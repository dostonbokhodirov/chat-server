package uz.doston.chatserver.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.doston.chatserver.dto.chat.ChatCreateDTO;
import uz.doston.chatserver.dto.chat.ChatCreateShortDTO;
import uz.doston.chatserver.dto.chat.ChatDTO;
import uz.doston.chatserver.entity.Chat;
import uz.doston.chatserver.exceptions.BadRequestException;
import uz.doston.chatserver.mapper.ChatMapper;
import uz.doston.chatserver.repository.ChatRepository;
import uz.doston.chatserver.response.DataDTO;
import uz.doston.chatserver.response.ResponseEntity;
import uz.doston.chatserver.service.base.AbstractService;

import java.util.List;

@Service
@CacheConfig(cacheNames = "chatCache")
public class ChatService extends AbstractService<ChatMapper, ChatRepository> {

    private final UserService userService;

    public ChatService(ChatMapper mapper, ChatRepository repository, UserService userService) {
        super(mapper, repository);
        this.userService = userService;
    }

    @CacheEvict(cacheNames = "chats", allEntries = true)
    public ResponseEntity<DataDTO<Long>> create(ChatCreateShortDTO dto) {
        ChatCreateDTO createDTO = ChatCreateDTO
                .builder()
                .name(dto.getName())
                .users(userService.getAllByIds(dto.getUsers()))
                .build();
        Chat chat = mapper.fromCreateDTO(createDTO);
        repository.save(chat);
        return new ResponseEntity<>(new DataDTO<>(chat.getId()));
    }

    @Cacheable(cacheNames = "chats")
    public ResponseEntity<DataDTO<List<ChatDTO>>> getAll() {
        List<Chat> chatList = repository.findAll(Sort.by("createdAt").descending());
        List<ChatDTO> chatDTOList = mapper.toDTO(chatList);
        return new ResponseEntity<>(new DataDTO<>(chatDTOList, (long) chatList.size()));
    }

    @Cacheable(cacheNames = "chats", key = "#id", unless = "#result==null")
    public Chat getEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Chat not found"));
    }

    public ChatDTO getById(Long id) {
        Chat chat = repository.findById(id).orElseThrow(() -> new BadRequestException("Chat not found"));
        return mapper.toDTO(chat);
    }
}

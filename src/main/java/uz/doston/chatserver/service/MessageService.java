package uz.doston.chatserver.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.doston.chatserver.dto.message.MessageCreateDTO;
import uz.doston.chatserver.dto.message.MessageCreateShortDTO;
import uz.doston.chatserver.dto.message.MessageDTO;
import uz.doston.chatserver.entity.Chat;
import uz.doston.chatserver.entity.Message;
import uz.doston.chatserver.enums.MessageType;
import uz.doston.chatserver.exceptions.BadRequestException;
import uz.doston.chatserver.mapper.MessageMapper;
import uz.doston.chatserver.repository.MessageRepository;
import uz.doston.chatserver.response.DataDTO;
import uz.doston.chatserver.response.ResponseEntity;
import uz.doston.chatserver.service.base.AbstractService;
import uz.doston.chatserver.utils.FileUtils;

import java.util.List;

@Service
@CacheConfig(cacheNames = "messageCache")
public class MessageService extends AbstractService<MessageMapper, MessageRepository> {

    private final ChatService chatService;
    private final UserService userService;
    private final FileUtils utils;

    public MessageService(
            MessageMapper mapper,
            MessageRepository repository,
            ChatService chatService,
            UserService userService,
            FileUtils utils
    ) {
        super(mapper, repository);
        this.chatService = chatService;
        this.userService = userService;
        this.utils = utils;
    }

    @CacheEvict(cacheNames = "messages", allEntries = true)
    public ResponseEntity<DataDTO<Long>> create(MessageCreateShortDTO dto) {
        MessageCreateDTO createDTO = MessageCreateDTO
                .builder()
                .chat(chatService.getById(dto.getChat()))
                .author(userService.getById(dto.getAuthor()))
                .content(dto.getContent())
                .build();
        switch (dto.getType()) {
            case "text" -> createDTO.setContentType(MessageType.TEXT);
            case "images" -> {
                createDTO.setContentType(MessageType.IMAGE);
                Runnable runnable = () -> {
                    utils.createFile(dto);
                };
                runnable.run();
            }
            default -> throw new BadRequestException("Message type is invalid");
        }
        Message message = mapper.fromCreateDTO(createDTO);
        repository.save(message);
        return new ResponseEntity<>(new DataDTO<>(message.getId()));
    }

    @Cacheable(cacheNames = "messages")
    public ResponseEntity<DataDTO<List<MessageDTO>>> getAll(Long chatId) {
        List<Message> messageList = repository.findAllByChat(chatService.getEntityById(chatId));
        List<MessageDTO> messageDTOList = mapper.toDTO(messageList);
        return new ResponseEntity<>(new DataDTO<>(messageDTOList, (long) messageDTOList.size()));
    }

}

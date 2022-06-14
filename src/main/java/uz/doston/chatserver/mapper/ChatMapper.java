package uz.doston.chatserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.doston.chatserver.dto.chat.ChatCreateDTO;
import uz.doston.chatserver.dto.chat.ChatDTO;
import uz.doston.chatserver.entity.Chat;
import uz.doston.chatserver.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        uses = {UserMapper.class}
)
public interface ChatMapper extends GenericMapper<Chat, ChatDTO, ChatCreateDTO> {

    @Override
    Chat fromDTO(ChatDTO dto);

    @Override
    List<Chat> fromDTO(List<ChatDTO> dtoList);

    @Override
    ChatDTO toDTO(Chat entity);

    @Override
    List<ChatDTO> toDTO(List<Chat> entities);

    @Override
    Chat fromCreateDTO(ChatCreateDTO dto);
}

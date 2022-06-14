package uz.doston.chatserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.doston.chatserver.dto.message.MessageCreateDTO;
import uz.doston.chatserver.dto.message.MessageDTO;
import uz.doston.chatserver.entity.Message;
import uz.doston.chatserver.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        uses = {UserMapper.class, ChatMapper.class}
)
public interface MessageMapper extends GenericMapper<Message, MessageDTO, MessageCreateDTO> {

    @Override
    MessageDTO toDTO(Message entity);

    @Override
    List<MessageDTO> toDTO(List<Message> entities);

    @Override
    Message fromCreateDTO(MessageCreateDTO dto);

}

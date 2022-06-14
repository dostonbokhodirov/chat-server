package uz.doston.chatserver.dto.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.doston.chatserver.dto.base.GenericDTO;
import uz.doston.chatserver.dto.user.UserDTO;
import uz.doston.chatserver.enums.MessageType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO extends GenericDTO {
    private Long chatId;
    private UserDTO author;
    private MessageType contentType;
    private String content;
}

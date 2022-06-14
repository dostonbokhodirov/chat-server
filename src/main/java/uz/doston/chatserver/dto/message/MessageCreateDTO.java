package uz.doston.chatserver.dto.message;

import lombok.*;
import uz.doston.chatserver.dto.base.BaseDTO;
import uz.doston.chatserver.dto.chat.ChatDTO;
import uz.doston.chatserver.dto.user.UserDTO;
import uz.doston.chatserver.entity.Chat;
import uz.doston.chatserver.entity.User;
import uz.doston.chatserver.enums.MessageType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageCreateDTO implements BaseDTO {
    private ChatDTO chat;
    private UserDTO author;
    private MessageType contentType;
    private String content;
}

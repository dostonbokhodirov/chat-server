package uz.doston.chatserver.dto.chat;

import lombok.*;
import uz.doston.chatserver.dto.base.BaseDTO;
import uz.doston.chatserver.dto.user.UserDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatCreateDTO implements BaseDTO {
    private String name;
    private List<UserDTO> users;
}

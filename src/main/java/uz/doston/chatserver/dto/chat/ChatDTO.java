package uz.doston.chatserver.dto.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.doston.chatserver.dto.base.GenericDTO;
import uz.doston.chatserver.dto.user.UserDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDTO extends GenericDTO {
    private String name;
    private List<UserDTO> users;
}

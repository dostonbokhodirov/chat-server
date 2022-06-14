package uz.doston.chatserver.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.doston.chatserver.dto.base.GenericDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends GenericDTO {
    private String username;
}

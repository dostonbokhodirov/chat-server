package uz.doston.chatserver.dto.user;

import lombok.*;
import uz.doston.chatserver.dto.base.BaseDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO implements BaseDTO {
    @NotBlank
    private String username;
}

package uz.doston.chatserver.dto.chat;

import lombok.*;
import uz.doston.chatserver.dto.base.BaseDTO;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatCreateShortDTO implements BaseDTO {
    @NotBlank
    private String name;
    private List<Long> users;
}

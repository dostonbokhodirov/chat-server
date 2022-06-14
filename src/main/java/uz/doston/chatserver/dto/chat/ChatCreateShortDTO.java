package uz.doston.chatserver.dto.chat;

import lombok.*;
import uz.doston.chatserver.dto.base.BaseDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatCreateShortDTO implements BaseDTO {
    private String name;
    private List<Long> users;
}

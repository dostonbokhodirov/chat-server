package uz.doston.chatserver.dto.message;

import lombok.*;
import uz.doston.chatserver.dto.base.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageCreateShortDTO implements BaseDTO {
    private Long chat;
    private Long author;
    private String type;
    private String content;
    private String ext;
}

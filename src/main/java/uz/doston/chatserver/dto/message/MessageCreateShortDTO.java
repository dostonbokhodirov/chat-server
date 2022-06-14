package uz.doston.chatserver.dto.message;

import lombok.*;
import uz.doston.chatserver.dto.base.BaseDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageCreateShortDTO implements BaseDTO {

    @NotBlank
    private Long chat;

    @NotBlank
    private Long author;

    @NotBlank
    private String type;

    @NotBlank
    private String content;

    private String ext;

}

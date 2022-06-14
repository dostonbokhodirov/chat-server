package uz.doston.chatserver.dto.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class GenericDTO implements BaseDTO {

    @NotBlank
    public Long id;
    public Date createdAt;

}

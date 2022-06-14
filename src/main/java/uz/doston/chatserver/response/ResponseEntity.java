package uz.doston.chatserver.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ResponseEntity<T> implements BaseResponse, Serializable {

    private T data;
    private Integer status;

    public ResponseEntity(T data) {
        this(data, HttpStatus.OK);
    }

    public ResponseEntity(T data, HttpStatus status) {
        this.data = data;
        this.status = status.value();
    }

}

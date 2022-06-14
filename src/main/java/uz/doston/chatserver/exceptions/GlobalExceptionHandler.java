package uz.doston.chatserver.exceptions;

import org.hibernate.TypeMismatchException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.doston.chatserver.response.AppErrorDTO;
import uz.doston.chatserver.response.DataDTO;
import uz.doston.chatserver.response.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleNotFound(NotFoundException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleBadRequest(BadRequestException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleRuntime(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDTO<>(
                        new AppErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {CustomSQLException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleCustomSQL(CustomSQLException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.CONFLICT, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleConstraintViolation(ConstraintViolationException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @Override
    protected org.springframework.http.ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                                           HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new org.springframework.http.ResponseEntity<>(new DataDTO<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<DataDTO<AppErrorDTO>> handleTypeMismatchException(TypeMismatchException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<DataDTO<AppErrorDTO>> handleWebExchangeBindException(WebExchangeBindException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));

    }

}

package uz.doston.chatserver.controller;

import org.springframework.web.bind.annotation.*;
import uz.doston.chatserver.controller.base.AbstractController;
import uz.doston.chatserver.dto.user.UserCreateDTO;
import uz.doston.chatserver.dto.user.UserDTO;
import uz.doston.chatserver.response.DataDTO;
import uz.doston.chatserver.response.ResponseEntity;
import uz.doston.chatserver.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends AbstractController<UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody UserCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<DataDTO<List<UserDTO>>> getAll() {
        return service.getAll();
    }
}

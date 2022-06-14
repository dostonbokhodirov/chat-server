package uz.doston.chatserver.controller;

import org.springframework.web.bind.annotation.*;
import uz.doston.chatserver.controller.base.AbstractController;
import uz.doston.chatserver.dto.chat.ChatCreateShortDTO;
import uz.doston.chatserver.dto.chat.ChatDTO;
import uz.doston.chatserver.response.DataDTO;
import uz.doston.chatserver.response.ResponseEntity;
import uz.doston.chatserver.service.ChatService;

import java.util.List;

@RestController
@RequestMapping(value = "/chats")
public class ChatController extends AbstractController<ChatService> {

    public ChatController(ChatService service) {
        super(service);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody ChatCreateShortDTO dto) {
        return service.create(dto);
    }

    @PostMapping(value = "/get")
    public ResponseEntity<DataDTO<List<ChatDTO>>> getAll() {
        return service.getAll();
    }

}

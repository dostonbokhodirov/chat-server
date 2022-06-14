package uz.doston.chatserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.doston.chatserver.controller.base.AbstractController;
import uz.doston.chatserver.dto.message.MessageCreateShortDTO;
import uz.doston.chatserver.dto.message.MessageDTO;
import uz.doston.chatserver.response.DataDTO;
import uz.doston.chatserver.response.ResponseEntity;
import uz.doston.chatserver.service.MessageService;

import java.util.List;

@RestController
@RequestMapping(value = "/messages")
public class MessageController extends AbstractController<MessageService> {

    public MessageController(MessageService service) {
        super(service);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody MessageCreateShortDTO dto) {
        return service.create(dto);
    }

    @PostMapping(value = "/get")
    public ResponseEntity<DataDTO<List<MessageDTO>>> getAll(@RequestBody Long chatId) {
        return service.getAll(chatId);
    }

}

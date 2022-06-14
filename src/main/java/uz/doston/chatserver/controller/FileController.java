package uz.doston.chatserver.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.doston.chatserver.controller.base.AbstractController;
import uz.doston.chatserver.service.FileService;

@RestController
@RequestMapping(value = "/file")
public class FileController extends AbstractController<FileService> {

    public FileController(FileService service) {
        super(service);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) {
        return ResponseEntity
                .ok()
                .body(service.uploadFile(file));
    }

    @GetMapping(value = "/download/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        byte[] bytes = service.downloadFile(filename);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity
                .ok()
                .contentLength(bytes.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment;filename=\"" + filename + "\"")
                .body(resource);
    }

    @DeleteMapping(value = "/delete/{filename}")
    public String delete(@PathVariable String filename) {
        return service.delete(filename);
    }

}

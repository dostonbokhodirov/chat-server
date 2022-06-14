package uz.doston.chatserver.utils;

import org.springframework.stereotype.Component;
import uz.doston.chatserver.dto.message.MessageCreateShortDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class FileUtils {

    private static final String UPLOADS_PATH = "uploads";

    public void createFile(MessageCreateShortDTO dto) {
        String generatedName = getRandomString() + "." + dto.getExt();
        try {
            Files.createFile(getGeneratedPath(generatedName));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Path getGeneratedPath(String generatedName) {
        Path path = getPaths();
        checkPath(path);
        return path.resolve(generatedName);
    }

    private Path getPaths() {
        LocalDate now = LocalDate.now();
        return Paths.get(
                UPLOADS_PATH,
                String.valueOf(now.getYear()),
                String.valueOf(now.getMonthValue()),
                String.valueOf(now.getDayOfMonth())
        );
    }

    private void checkPath(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private String getRandomString() {
        return UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
    }
}

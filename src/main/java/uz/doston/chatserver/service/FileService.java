package uz.doston.chatserver.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.doston.chatserver.service.base.BaseService;

@Service
@RequiredArgsConstructor
public class FileService implements BaseService {
    private final AmazonS3 s3;

    @Value("${bucketName}")
    private String bucketName;

    @SneakyThrows
    public String uploadFile(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        String filename = file.getOriginalFilename();
        s3.putObject(bucketName, filename, file.getInputStream(), objectMetadata);
        return "File is successfully uploaded";
    }

    @SneakyThrows
    public byte[] downloadFile(String filename) {
        S3Object object = s3.getObject(bucketName, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        return IOUtils.toByteArray(objectContent);

    }

    public String delete(String filename) {
        s3.deleteObject(bucketName, filename);
        return "deleted";
    }

}

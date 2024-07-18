package io.muzoo.ssc.springwebapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;


@Service
public class ImageService {

    public Boolean saveImageToStorage(String username, MultipartFile imageFile) throws IOException {

        if (imageFile == null || imageFile.isEmpty()) {
            return false;
        }
        String uniqueFileName = username + ".jpg";

        Path uploadPath = Path.of("../imageStorage");
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return true;
    }

    // To view an image
    public String getImage(String username) throws IOException {
        Path uploadPath = Path.of("../imageStorage");
        String filename = username + ".jpg";
        Path filePath = uploadPath.resolve(filename);


        if (Files.exists(filePath)) {
            byte[] imageBytes = Files.readAllBytes(filePath);
            // convert byte array to base64 string for frontend
            return Base64.getEncoder().encodeToString(imageBytes);
        } else {
            return null; // Handle missing images
        }
    }

    // Delete an image
    public Boolean deleteImage(String username) throws IOException {
        Path uploadPath = Path.of("../imageStorage");
        String filename = username;
        Path filePath = uploadPath.resolve(filename);

        if (Files.exists(filePath)) {
            Files.delete(filePath);
            return true;
        }

        return false;
    }
}
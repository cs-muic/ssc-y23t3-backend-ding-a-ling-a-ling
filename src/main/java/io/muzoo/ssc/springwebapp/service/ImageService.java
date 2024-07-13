package io.muzoo.ssc.springwebapp.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.Track;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@Service
public class ImageService {

    public Boolean saveImageToStorage(String username, MultipartFile imageFile) throws IOException {

        if (imageFile == null || imageFile.isEmpty()) {
            return false;
        }

        System.out.println("the username is " + username);
        String uniqueFileName = username + "uu.jpg";

        Path uploadPath = Path.of("imageStorage", username);
        Path filePath = uploadPath.resolve(uniqueFileName);

        System.out.println("the upload path is " + uploadPath); // try check if this is working
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return true;
    }

    // To view an image
    public byte[] getImage(String username) throws IOException {
        Path uploadPath = Path.of("imageStorage");
        String filename = username + ".jpg";
        Path filePath = uploadPath.resolve(filename);

        System.out.println("getImage path: " + filePath);

        if (Files.exists(filePath)) {
            System.out.println("Image exists");
            byte[] imageBytes = Files.readAllBytes(filePath);
            return imageBytes;
        } else {
            System.out.println("Image does not exist");
            return null; // Handle missing images
        }
    }

    // Delete an image
    public Boolean deleteImage(String username) throws IOException {
        Path uploadPath = Path.of("imageStorage");
        String filename = username;
        Path filePath = uploadPath.resolve(filename);

        if (Files.exists(filePath)) {
            Files.delete(filePath);
            return true;
        }

        return false;
    }
}
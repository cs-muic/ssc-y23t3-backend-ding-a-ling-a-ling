package io.muzoo.ssc.springwebapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class ImageService {

    UserService userService;

    // Save image in a local directory
    public String saveImageToStorage(String username, String uploadDirectory, MultipartFile imageFile) throws IOException {
        String uniqueFileName = username + "_" + new Date().getTime();
        System.out.println("the unique file name is " + uniqueFileName); // try check if this is working

        Path uploadPath = Path.of(uploadDirectory, username);
        Path filePath = uploadPath.resolve(uniqueFileName);

        System.out.println("the upload path is " + uploadPath); // try check if this is working
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }

    // To view an image
    public byte[] getImage(String imageDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null; // Handle missing images
        }
    }

    // Delete an image
    public String deleteImage(String imageDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed"; // Handle missing images
        }
    }
}
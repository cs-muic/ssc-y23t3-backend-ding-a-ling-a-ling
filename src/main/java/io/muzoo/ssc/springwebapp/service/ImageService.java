package io.muzoo.ssc.springwebapp.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

@Service
public class ImageService {

    public String saveImageToStorage(String username, String uploadDirectory, MultipartFile imageFile) throws IOException {
        System.out.println("current syste, currentTime" + ZonedDateTime.now());
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS");
//        String uniqueFileName = username + "_" + ZonedDateTime.now();
        String uniqueFileName = username + "_" + now.format(formatter);
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

    public String replaceImage(String username, String uploadDirectory, MultipartFile replaceFile) {
        if (replaceFile == null || replaceFile.isEmpty()) {
            return "Failed: Replace file is null or empty";
        }

        String originalFileName = StringUtils.cleanPath(replaceFile.getOriginalFilename());
        String uniqueFileName = username + "_" + System.currentTimeMillis() + "_" + originalFileName;
        Path userDirectory = Paths.get(uploadDirectory, username);

        try {
            Files.list(userDirectory)
                    .filter(Files::isRegularFile)
                    .forEach(this::deleteFile);

            Path filePath = userDirectory.resolve(originalFileName);
            Files.copy(replaceFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed: " + e.getMessage();
        }
    }

    public byte[] getImageBytes(String username, String uploadDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(uploadDirectory, username, imageName);

        if (Files.exists(imagePath)) {
            return Files.readAllBytes(imagePath);
        } else {
            throw new IOException("Image file not found: " + imageName);
        }
    }

    private void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + path.toString());
            e.printStackTrace();
        }
    }

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
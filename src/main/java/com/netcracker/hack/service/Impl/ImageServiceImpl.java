package com.netcracker.hack.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.service.ImageService;

@Component
public class ImageServiceImpl implements ImageService {

  private final Path rootLocation = Paths.get("./src/main/resources/static/img/avatars");


  @Override
  public void saveImage(MultipartFile image, UserDTO userDTO) {
    
    String filename = StringUtils.cleanPath(userDTO.getUuid().toString());

    try (InputStream inputStream = image.getInputStream()) {

      Files.copy(inputStream, rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

    } catch (IOException e) {
      throw new RuntimeException("Failed to store file " + filename, e);
    }

  }

}

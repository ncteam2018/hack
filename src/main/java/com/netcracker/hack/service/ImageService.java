package com.netcracker.hack.service;

import org.springframework.web.multipart.MultipartFile;
import com.netcracker.hack.dto.UserDTO;

public interface ImageService {

  public void saveImage(MultipartFile image, UserDTO userDTO);
}

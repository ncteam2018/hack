package com.netcracker.hack.controller.rest;

import java.security.Principal;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.netcracker.hack.service.ImageService;
import com.netcracker.hack.service.ProfileService;

@RestController
@RequestMapping("/api/image")
public class ImageController {

  @Autowired
  private ImageService imageService;

  @Autowired
  private ProfileService profileService;

  @PostMapping
  public void saveImage(@RequestParam("file") MultipartFile image, Principal userPrincipal, HttpServletResponse httpServletResponse) {


    imageService.saveImage(image, profileService.getUserDTOByLogin(userPrincipal.getName()));

    httpServletResponse.setHeader("Location", "/profile");
    httpServletResponse.setStatus(302);

  }
}

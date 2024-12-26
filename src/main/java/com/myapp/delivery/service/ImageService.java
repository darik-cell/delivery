package com.myapp.delivery.service;

import com.myapp.delivery.domain.menu_item.MenuItemImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageService {

  String upload(MenuItemImage image);
  InputStream getImage(String fileName);
}

package com.myapp.delivery.domain.menu_item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuItemImage {

  private MultipartFile file;
}

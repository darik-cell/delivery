package com.myapp.delivery.web.dto.menu_item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuItemImageDto {

  private MultipartFile file;
}

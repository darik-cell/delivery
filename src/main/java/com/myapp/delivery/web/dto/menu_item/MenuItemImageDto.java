package com.myapp.delivery.web.dto.menu_item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuItemImageDto {

  @Schema(description = "Загружаемый файл. Поддерживаются формат PNG.", format = "binary")
  private MultipartFile file;
}

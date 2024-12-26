package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.domain.menu_item.MenuItemImage;
import com.myapp.delivery.service.ImageService;
import com.myapp.delivery.service.MenuItemService;
import com.myapp.delivery.web.dto.menu_item.MenuItemDto;
import com.myapp.delivery.web.dto.menu_item.MenuItemImageDto;
import com.myapp.delivery.web.mapper.MenuItemImageMapper;
import com.myapp.delivery.web.mapper.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menuitems")
@RequiredArgsConstructor
public class MenuItemController {

  private final MenuItemService menuItemService;
  private final MenuItemMapper menuItemMapper;
  private final ImageService imageService;
  private final MenuItemImageMapper menuItemImageMapper;

  @PutMapping
  public MenuItem updateMenuItem(@RequestBody MenuItem menuItem) {
    menuItem.setRestaurantId(1L);
    return menuItemService.update(menuItem);
  }

  @PostMapping
  public MenuItemDto createMenuItem(@RequestBody MenuItemDto menuItemDto) {
    System.out.println(menuItemDto);
    menuItemDto.setRestaurantId(1L);
    return menuItemMapper.toDto(menuItemService.create(menuItemMapper.toEntity(menuItemDto)));
  }

  @DeleteMapping("/{id}")
  public void deleteMenuItem(@PathVariable Long id) {
    menuItemService.delete(id);
  }

  @GetMapping("/{id}")
  public MenuItemDto getMenuItem(@PathVariable Long id) {
    return menuItemMapper.toDto(menuItemService.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Нету такого menu item с id: " + id)
            ));
  }

  @GetMapping
  public List<MenuItemDto> getMenuItems() {
    return menuItemMapper.toDto(menuItemService.findAll());
  }

  @PostMapping("/{id}/image")
  public void uploadImage(@PathVariable Long id,
                          @ModelAttribute MenuItemImageDto imageDto) {
    MenuItemImage image = menuItemImageMapper.toEntity(imageDto);
    menuItemService.uploadImage(id, image);
  }

  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
    try {
      String imageName = menuItemService.findById(id).get().getImageUrl();
      InputStream imageStream = menuItemService.getImage(id);

      byte[] imageBytes = imageStream.readAllBytes();

      // Определяем тип контента (если изображения jpg или png)
      String contentType = imageName.endsWith(".jpg") ? MediaType.IMAGE_JPEG_VALUE :
              imageName.endsWith(".png") ? MediaType.IMAGE_PNG_VALUE :
                      MediaType.APPLICATION_OCTET_STREAM_VALUE;

      return ResponseEntity.ok()
              .contentType(MediaType.parseMediaType(contentType))
              .body(imageBytes);
    } catch (
            Exception e) {
      // Возвращаем ошибку 404, если изображение не найдено
      return ResponseEntity.notFound().build();
    }
  }

}

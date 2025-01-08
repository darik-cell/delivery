package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.domain.menu_item.MenuItemImage;
import com.myapp.delivery.service.ImageService;
import com.myapp.delivery.service.MenuItemService;
import com.myapp.delivery.web.controller.swagger.MenuItemControllerApi;
import com.myapp.delivery.web.dto.menu_item.MenuItemDto;
import com.myapp.delivery.web.dto.menu_item.MenuItemImageDto;
import com.myapp.delivery.web.mapper.MenuItemImageMapper;
import com.myapp.delivery.web.mapper.MenuItemMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Tag(name = "Меню")
@RestController
@RequestMapping("/api/v1/menuitems")
@RequiredArgsConstructor
public class MenuItemController implements MenuItemControllerApi {

  private final MenuItemService menuItemService;
  private final MenuItemMapper menuItemMapper;
  private final MenuItemImageMapper menuItemImageMapper;

  @Override
  @PutMapping
  public MenuItem updateMenuItem(@RequestBody MenuItemDto menuItem) {
    menuItem.setRestaurantId(1L);
    return menuItemService.update(menuItemMapper.toEntity(menuItem));
  }

  @Override
  @PostMapping
  public MenuItemDto createMenuItem(@RequestBody MenuItemDto menuItemDto) {
    System.out.println(menuItemDto);
    menuItemDto.setRestaurantId(1L);
    return menuItemMapper.toDto(menuItemService.create(menuItemMapper.toEntity(menuItemDto)));
  }

  @Operation(
          summary = "Удалить элемент меню"
  )
  @DeleteMapping("/{id}")
  public void deleteMenuItem(@PathVariable @Parameter(description = "id элемента меню") Long id) {
    menuItemService.delete(id);
  }

  @Operation(
          summary = "Получить элемент меню по id"
  )
  @GetMapping("/{id}")
  public MenuItemDto getMenuItem(@PathVariable @Parameter(description = "id элемента меню") Long id) {
    return menuItemMapper.toDto(menuItemService.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Нету такого menu item с id: " + id)
            ));
  }

  @Operation(
          summary = "Получить все элементы меню"
  )
  @GetMapping
  public List<MenuItemDto> getMenuItems() {
    return menuItemMapper.toDto(menuItemService.findAll());
  }

  @Operation(
          summary = "Загрузить изображение для элемента меню"
  )
  @PostMapping("/{id}/image")
  public void uploadImage(@PathVariable @Parameter(description = "id элемента меню") Long id,
                          @ModelAttribute MenuItemImageDto imageDto) {
    MenuItemImage image = menuItemImageMapper.toEntity(imageDto);
    menuItemService.uploadImage(id, image);
  }

  @Operation(
          summary = "Получить изображение для элемента меню"
  )
  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getImage(@PathVariable @Parameter(description = "id элемента меню") Long id) {
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

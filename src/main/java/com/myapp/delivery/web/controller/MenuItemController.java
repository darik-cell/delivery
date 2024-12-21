package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.service.MenuItemService;
import com.myapp.delivery.web.dto.menu_item.MenuItemDto;
import com.myapp.delivery.web.mapper.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menuitems")
@RequiredArgsConstructor
public class MenuItemController {

  private final MenuItemService menuItemService;
  private final MenuItemMapper menuItemMapper;

  @PutMapping
  public MenuItem updateMenuItem(@RequestBody MenuItem menuItem) {
    menuItem.setRestaurantId(1L);
    return menuItemService.update(menuItem);
  }

  @PostMapping
  public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
    menuItem.setRestaurantId(1L);
    return menuItemService.create(menuItem);
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

}

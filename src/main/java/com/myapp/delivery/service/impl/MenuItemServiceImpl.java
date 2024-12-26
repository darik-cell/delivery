package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.domain.menu_item.MenuItemImage;
import com.myapp.delivery.repository.MenuItemRepository;
import com.myapp.delivery.service.ImageService;
import com.myapp.delivery.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

  private final MenuItemRepository menuItemRepository;
  private final ImageService imageService;


  @Override
  public Optional<MenuItem> findById(Long id) {
    return menuItemRepository.findById(id);
  }

  @Override
  public List<MenuItem> findAll() {
    return menuItemRepository.findAll();
  }

  @Override
  public MenuItem create(MenuItem menuItem) {
    menuItemRepository.insertMenuItem(menuItem);
    menuItemRepository.insertMenuItemIngredients(menuItem.getIngredients(), menuItem.getId());
    return menuItem;
  }


  @Override
  public MenuItem update(MenuItem menuItem) {
    menuItemRepository.updateMenuItem(menuItem);
    menuItemRepository.updateMenuItemIngredients(menuItem.getIngredients(), menuItem.getId());
    return menuItem;
  }

  @Override
  public void delete(Long id) {
    menuItemRepository.delete(id);
  }

  @Override
  public void uploadImage(Long id, MenuItemImage image) {
    MenuItem menuItem = menuItemRepository.findById(id).get();
    String fileName = imageService.upload(image);
    menuItem.setImageUrl(fileName);
    menuItemRepository.updateMenuItem(menuItem);
  }

  @Override
  public InputStream getImage(Long id) {
    MenuItem menuItem = menuItemRepository.findById(id).get();
    String imageUrl = menuItem.getImageUrl();
    return imageService.getImage(imageUrl);
  }
}

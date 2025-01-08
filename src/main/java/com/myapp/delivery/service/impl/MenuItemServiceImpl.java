package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.domain.menu_item.MenuItemImage;
import com.myapp.delivery.repository.MenuItemRepository;
import com.myapp.delivery.service.ImageService;
import com.myapp.delivery.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

  private final MenuItemRepository menuItemRepository;
  private final ImageService imageService;
  private final Logger logger = Logger.getLogger(MenuItemServiceImpl.class.getName());

  @Override
  public Optional<MenuItem> findById(Long id) {
    return menuItemRepository.findById(id);
  }

  @Override
  public List<MenuItem> findAllAvailable() {
    return menuItemRepository.findAllAvailable();
  }

  @Override
  public List<MenuItem> findAllArchive() {
    return menuItemRepository.findAllArchive();
  }

  @Override
  public MenuItem create(MenuItem menuItem) {
    menuItemRepository.insertMenuItem(menuItem);
    menuItemRepository.insertMenuItemIngredients(menuItem.getIngredients(), menuItem.getId());
    return menuItem;
  }


  @Override
  public MenuItem update(MenuItem menuItem) {
    menuItem.setRestaurantId(1L);
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
    menuItemRepository.updateMenuItemImage(menuItem);
  }

  @Override
  public InputStream getImage(Long id) {
    MenuItem menuItem = menuItemRepository.findById(id).get();
    logger.info(menuItem.toString());
    String imageUrl = menuItem.getImageUrl();
    logger.info("imageUrl:" + imageUrl);
    return imageService.getImage(imageUrl);
  }
}

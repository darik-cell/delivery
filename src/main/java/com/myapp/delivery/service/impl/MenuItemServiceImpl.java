package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.repository.MenuItemRepository;
import com.myapp.delivery.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

  private final MenuItemRepository menuItemRepository;


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
}

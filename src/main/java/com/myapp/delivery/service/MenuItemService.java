package com.myapp.delivery.service;

import com.myapp.delivery.domain.menu_item.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemService {

  Optional<MenuItem> findById(Long id);
  List<MenuItem> findAll();

  MenuItem create(MenuItem menuItem);
  MenuItem update(MenuItem menuItem);
  void delete(Long id);
}

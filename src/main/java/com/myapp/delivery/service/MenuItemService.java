package com.myapp.delivery.service;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.domain.menu_item.MenuItemImage;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface MenuItemService {

  Optional<MenuItem> findById(Long id);
  List<MenuItem> findAllAvailable();
  List<MenuItem> findAllArchive();

  MenuItem create(MenuItem menuItem);
  MenuItem update(MenuItem menuItem);
  void delete(Long id);

  void uploadImage(Long id, MenuItemImage image);
  InputStream getImage(Long id);
}

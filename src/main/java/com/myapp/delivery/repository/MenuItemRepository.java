package com.myapp.delivery.repository;

import com.myapp.delivery.domain.ingredient.Ingredient;
import com.myapp.delivery.domain.menu_item.MenuItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Mapper
public interface MenuItemRepository {
  Optional<MenuItem> findById(Long id);
  List<MenuItem> findAllAvailable();
  List<MenuItem> findAllArchive();

  void insertMenuItem(MenuItem menuItem);
  void insertMenuItemIngredients(@Param("ingredients") Set<Ingredient> ingredients, @Param("menuItemId") long menuItemId);

  void updateMenuItemImage(MenuItem menuItem);
  void updateMenuItem(MenuItem menuItem);
  void updateMenuItemIngredients(@Param("ingredients") Set<Ingredient> ingredients, @Param("menuItemId") long menuItemId);

  void delete(Long id);
}

package com.myapp.delivery.repository;

import com.myapp.delivery.domain.ingredient.Ingredient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface IngredientRepository {

  List<Ingredient> findAll();
  void create(Ingredient ingredient);
  void update(Ingredient ingredient);
  void delete(Long id);
}

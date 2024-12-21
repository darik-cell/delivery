package com.myapp.delivery.service;

import com.myapp.delivery.domain.ingredient.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientService {
  List<Ingredient> findAll();

  Ingredient create(Ingredient ingredient);

  Ingredient update(Ingredient ingredient);

  void delete(Long id);
}

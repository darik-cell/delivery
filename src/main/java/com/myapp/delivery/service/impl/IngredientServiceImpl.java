package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.ingredient.Ingredient;
import com.myapp.delivery.repository.IngredientRepository;
import com.myapp.delivery.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
  private final IngredientRepository ingredientRepository;

  @Override
  public List<Ingredient> findAll() {
    return ingredientRepository.findAll();
  }

  @Override
  public Ingredient create(Ingredient ingredient) {
    ingredientRepository.create(ingredient);
    return ingredient;
  }

  @Override
  public Ingredient update(Ingredient ingredient) {
    ingredientRepository.update(ingredient);
    return ingredient;
  }

  @Override
  public void delete(Long id) {
    ingredientRepository.delete(id);
  }
}

package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.ingredient.Ingredient;
import com.myapp.delivery.service.IngredientService;
import com.myapp.delivery.web.dto.ingredient.IngredientDto;
import com.myapp.delivery.web.mapper.IngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingredients")
public class IngredientsController {
  private final IngredientService ingredientService;
  private final IngredientMapper ingredientMapper;

  @PostMapping
  public IngredientDto createIngredient(@RequestBody Ingredient ingredient) {
    return ingredientMapper.toDto(ingredientService.create(ingredient));
  }

  @GetMapping
  public List<IngredientDto> findAllIngredients() {
    List<Ingredient> ingredients = ingredientService.findAll();
    return ingredientMapper.toDto(ingredients);
  }

  @PutMapping
  public IngredientDto updateIngredient(@RequestBody IngredientDto ingredientDto) {
    Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
    return ingredientMapper.toDto(ingredientService.update(ingredient));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    ingredientService.delete(id);
  }
}

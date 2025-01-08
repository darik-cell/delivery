package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.ingredient.Ingredient;
import com.myapp.delivery.service.IngredientService;
import com.myapp.delivery.web.dto.ingredient.IngredientDto;
import com.myapp.delivery.web.mapper.IngredientMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ингридиенты")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingredients")
public class IngredientsController {
  private final IngredientService ingredientService;
  private final IngredientMapper ingredientMapper;

  @Operation(
          summary = "создать ингридиент"
  )
  @PostMapping
  public IngredientDto createIngredient(@RequestBody IngredientDto ingredient) {
    return ingredientMapper.toDto(ingredientService.create(
            ingredientMapper.toEntity(ingredient)
    ));
  }

  @Operation(
          summary = "Получить все ингридиенты"
  )
  @GetMapping
  public List<IngredientDto> findAllIngredients() {
    List<Ingredient> ingredients = ingredientService.findAll();
    return ingredientMapper.toDto(ingredients);
  }

  @Operation(
          summary = "Обновить ингридиент"
  )
  @PutMapping
  public IngredientDto updateIngredient(@RequestBody IngredientDto ingredientDto) {
    Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
    return ingredientMapper.toDto(ingredientService.update(ingredient));
  }

  @Operation(
          summary = "Удалить ингридиент"
  )
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable @Parameter(description = "id клиента") Long id) {
    ingredientService.delete(id);
  }
}

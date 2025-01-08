package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.web.dto.ingredient.IngredientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Ингридиенты")
public interface IngredientsControllerApi {

  @Operation(
          summary = "создать ингридиент"
  )
  public IngredientDto createIngredient(IngredientDto ingredient);

  @Operation(
          summary = "Получить все ингридиенты"
  )
  public List<IngredientDto> findAllIngredients();

  @Operation(
          summary = "Обновить ингридиент"
  )
  public IngredientDto updateIngredient(IngredientDto ingredientDto);

  @Operation(
          summary = "Удалить ингридиент"
  )
  public void deleteById(@Parameter(description = "id клиента") Long id);
}

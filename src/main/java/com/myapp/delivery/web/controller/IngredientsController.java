package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.ingredient.Ingredient;
import com.myapp.delivery.service.IngredientService;
import com.myapp.delivery.web.controller.swagger.IngredientsControllerApi;
import com.myapp.delivery.web.dto.ingredient.IngredientDto;
import com.myapp.delivery.web.mapper.IngredientMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingredients")
public class IngredientsController implements IngredientsControllerApi {
  private final IngredientService ingredientService;
  private final IngredientMapper ingredientMapper;

  @PostMapping
  public IngredientDto createIngredient(@RequestBody IngredientDto ingredient) {
    return ingredientMapper.toDto(ingredientService.create(
            ingredientMapper.toEntity(ingredient)
    ));
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

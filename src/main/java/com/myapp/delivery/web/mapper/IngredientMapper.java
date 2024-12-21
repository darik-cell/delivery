package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.ingredient.Ingredient;
import com.myapp.delivery.web.dto.ingredient.IngredientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

  Ingredient toEntity(IngredientDto ingredientDto);

  IngredientDto toDto(Ingredient ingredient);

  List<IngredientDto> toDto(List<Ingredient> ingredients);
}

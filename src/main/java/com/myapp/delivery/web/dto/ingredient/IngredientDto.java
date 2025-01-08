package com.myapp.delivery.web.dto.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

  @Schema(description = "Уникальный идентификатор", example = "1")
  private int id;

  @Schema(description = "Название ингридиента", example = "Говядина")
  private String name;
}

package com.myapp.delivery.web.dto.menu_item;

import com.myapp.delivery.domain.menu_item.Category;
import com.myapp.delivery.web.dto.ingredient.IngredientDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class MenuItemDto {
  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private Category category;
  private String imageUrl;
  private Boolean availabilityStatus;
  private int weight;
  private int calories;
  private Set<IngredientDto> ingredients;
}

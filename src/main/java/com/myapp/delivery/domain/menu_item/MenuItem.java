package com.myapp.delivery.domain.menu_item;

import com.myapp.delivery.domain.ingredient.Ingredient;
import com.myapp.delivery.domain.kitchen.Kitchen;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Data
public class MenuItem {

  private Long id;
  private Long restaurantId;
  private String name;
  private String description;
  private BigDecimal price;
  private Category category;
  private String imageUrl;
  private Boolean availabilityStatus;
  private Kitchen kitchen;

  private int weight;
  private int calories;
  private Set<Ingredient> ingredients;

  private Timestamp createdAt;
  private Timestamp updatedAt;
}

package com.myapp.delivery.domain.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
public class Ingredient {

  private int id;
  private String name;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}

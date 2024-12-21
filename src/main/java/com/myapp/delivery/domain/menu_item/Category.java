package com.myapp.delivery.domain.menu_item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Category {
  STARTER("Закуска"),
  MAIN_COURSE("Основное блюдо"),
  SIDE_DISH("Гарнир"),
  SALAD("Салат"),
  DRINK("Напиток"),
  SOUP("Суп"),
  DESSERT("Десерт");

  private final String dbValue;

  Category(String dbValue) {
    this.dbValue = dbValue;
  }

  @JsonCreator
  public static Category fromDbValue(String dbValue) {
    for (Category category : Category.values()) {
      if (category.dbValue.equals(dbValue)) {
        return category;
      }
    }
    throw new IllegalArgumentException("Unknown category: " + dbValue);
  }

  @JsonValue
  public String getDbValue() {
    return dbValue;
  }
}

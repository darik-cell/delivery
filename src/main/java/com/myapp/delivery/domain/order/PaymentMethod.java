package com.myapp.delivery.domain.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;

public enum PaymentMethod {
  CARD("Карта"),
  CASH("Наличные");

  private final String dbValue;

  PaymentMethod(String dbValue) {
    this.dbValue = dbValue;
  }

  // Метод для поиска категории по названию из базы
  @JsonCreator
  public static PaymentMethod fromDbValue(String dbValue) {
    for (PaymentMethod method : PaymentMethod.values()) {
      if (method.dbValue.equals(dbValue)) {
        return method;
      }
    }
    throw new IllegalArgumentException("Unknown category: " + dbValue);
  }

  @JsonValue
  public String getDbValue() {
    return dbValue;
  }
}

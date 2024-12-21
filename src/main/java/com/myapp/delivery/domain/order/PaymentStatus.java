package com.myapp.delivery.domain.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum PaymentStatus {
  PAID("Оплачено"),
  UNPAID("Не оплачено");

  private final String dbValue;

  PaymentStatus(String dbValue) {
    this.dbValue = dbValue;
  }

  // Метод для поиска статуса по значению из базы
  @JsonCreator
  public static PaymentStatus fromDbValue(String dbValue) {
    for (PaymentStatus status : PaymentStatus.values()) {
      if (status.dbValue.equals(dbValue)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown payment status: " + dbValue);
  }

  @JsonValue
  public String getDbValue() {
    return dbValue;
  }
}

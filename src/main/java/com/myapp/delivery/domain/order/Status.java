package com.myapp.delivery.domain.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Status {
  PENDING("в обработке"),
  PREPARING("готовится"),
  ASSIGNED_COURIER("назначен курьер"),
  ON_THE_WAY("в пути"),
  DELIVERED("доставлен"),
  CANCELED("отменен");

  private final String dbValue;

  Status(String dbValue) {
    this.dbValue = dbValue;
  }

  // Метод для поиска статуса по значению из базы
  @JsonCreator
  public static Status fromDbValue(String dbValue) {
    for (Status status : Status.values()) {
      if (status.dbValue.equals(dbValue)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown status: " + dbValue);
  }

  @JsonValue
  public String getDbValue() {
    return dbValue;
  }
}

package com.myapp.delivery.domain.courier;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Courier {

  private long userId;
  private String name;
  private String phone;
  private boolean isOnShift;
  private boolean isOnDelivery;
  private Timestamp lastShiftStart;
  private Timestamp lastShiftEnd;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}

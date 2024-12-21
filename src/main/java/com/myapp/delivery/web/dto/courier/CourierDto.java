package com.myapp.delivery.web.dto.courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourierDto {

  private long userId;
  private boolean isOnShift;
  private boolean isOnDelivery;
}

package com.myapp.delivery.web.dto.courier;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourierDto {

  @Schema(description = "Уникальный идентификатор", example = "1")
  private long userId;

  @Schema(description = "Флаг для обозначения рабочаяя смена ли сейчас", example = "true")
  private boolean isOnShift;

  @Schema(description = "Флаг для обозначения на доставке ли сейчас курьер", example = "true")
  private boolean isOnDelivery;
}

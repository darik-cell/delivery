package com.myapp.delivery.web.dto.order;

import com.myapp.delivery.domain.order.PaymentMethod;
import com.myapp.delivery.domain.order.PaymentStatus;
import com.myapp.delivery.domain.order.Status;
import com.myapp.delivery.domain.order_item.OrderItem;
import com.myapp.delivery.web.dto.order_item.OrderItemDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderDto {

  @Schema(description = "Уникальный идентификатор заказа", example = "1")
  private Long id;

  @Schema(description = "Уникальный идентификатор пользователя", example = "1")
  private int customerId;

  @Schema(description = "Статус заказа",
          allowableValues = {"в обработке", "готовится", "назначен курьер", "в пути", "доставлен", "отменен"},
          accessMode = Schema.AccessMode.READ_ONLY)
  private Status status;

  @Schema(description = "Цена заказа", example = "1890")
  private BigDecimal totalPrice;

  @Schema(description = "Позиции из заказа")
  private List<OrderItemDto> orderItems;

  @Schema(description = "Способ оплаты", allowableValues = {"Карта", "Наличные"})
  private PaymentMethod paymentMethod;

  @Schema(description = "Статус оплаты", allowableValues = {"Оплачено", "Не оплачено"})
  private PaymentStatus paymentStatus;

  @Schema(description = "Время, когда курьер доставил")
  private String deliveryTime;

  @Schema(description = "Адрес доставки", example = "Московское шоссе 34б")
  private String deliveryAddress;

  @Schema(description = "Уникальный идентификатор курьера", example = "2")
  private Long courierId;

  @Schema(description = "Время создания заказа")
  private String createdAt;
}

package com.myapp.delivery.web.dto.order;

import com.myapp.delivery.domain.order.PaymentMethod;
import com.myapp.delivery.domain.order.PaymentStatus;
import com.myapp.delivery.domain.order.Status;
import com.myapp.delivery.domain.order_item.OrderItem;
import com.myapp.delivery.web.dto.order_item.OrderItemDto;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderDto {

  private Long id;

  private int customerId;

  private Status status;

  private BigDecimal totalPrice;

  private List<OrderItemDto> orderItems;

  private PaymentMethod paymentMethod;

  private PaymentStatus paymentStatus;

  private Timestamp orderTime;

  private String deliveryAddress;

  private Long courierId;
}

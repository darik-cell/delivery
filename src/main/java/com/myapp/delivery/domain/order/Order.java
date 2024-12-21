package com.myapp.delivery.domain.order;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.domain.order_item.OrderItem;
import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Order {

  private Long id;
  private int customerId;
  private int restaurantId;
  private Status status;
  private BigDecimal totalPrice;
  private List<OrderItem> orderItems;
  private PaymentMethod paymentMethod;
  private PaymentStatus paymentStatus;
  private Timestamp orderTime;
  private String deliveryAddress;
  private Timestamp deliveryTime;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Long courierId;
}

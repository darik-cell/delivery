package com.myapp.delivery.domain.order_item;

import com.myapp.delivery.domain.menu_item.MenuItem;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderItem {

  private Long id;
  private Long orderId;
  private MenuItem menuItem;
  private int quantity;
  private BigDecimal priceAtOrderTime;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}

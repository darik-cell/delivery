package com.myapp.delivery.web.dto.order_item;

import com.myapp.delivery.web.dto.menu_item.MenuItemDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

  private Long id;
  private Long orderId;
  private MenuItemDto menuItem;
  private int quantity;
  private BigDecimal priceAtOrderTime;
}

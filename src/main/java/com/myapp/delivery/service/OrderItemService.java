package com.myapp.delivery.service;

import com.myapp.delivery.domain.order_item.OrderItem;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderItemService {

  List<OrderItem> getOrderItemsByOrderId(Long orderId);
  Optional<OrderItem> getOrderItemById(Long id);

  OrderItem create(OrderItem orderItem);
  OrderItem update(OrderItem orderItem);
  void delete(long id);
}

package com.myapp.delivery.service;

import com.myapp.delivery.domain.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

  Optional<Order> getOrderByOrderId(long id);
  List<Order> getAllUserOrdersByUserId(long id);
  List<Order> getAllActualUserOrdersByUserId(long id);
  List<Order> getAllOrders();

  boolean setOrderStatus(long id, String status);
  boolean setCourier(Long id, Long courierId);

  Order create(Order order);
}

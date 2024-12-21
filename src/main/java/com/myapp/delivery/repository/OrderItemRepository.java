package com.myapp.delivery.repository;

import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.domain.order_item.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface OrderItemRepository {

  List<OrderItem> findOrderItemsByOrderId(Long orderId);
  Optional<OrderItem> findOrderItemById(Long id);

  void create(OrderItem orderItem);
  void createOrderItems(List<OrderItem> orderItems);
  void update(OrderItem orderItem);
  void delete(long id);
}

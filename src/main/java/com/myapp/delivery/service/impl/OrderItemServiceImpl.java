package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.order_item.OrderItem;
import com.myapp.delivery.repository.OrderItemRepository;
import com.myapp.delivery.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

  private final OrderItemRepository orderItemRepository;

  @Override
  public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
    return orderItemRepository.findOrderItemsByOrderId(orderId);
  }

  @Override
  public Optional<OrderItem> getOrderItemById(Long id) {
    return orderItemRepository.findOrderItemById(id);
  }

  @Override
  public OrderItem create(OrderItem orderItem) {
    orderItemRepository.create(orderItem);
    return orderItem;
  }

  @Override
  public OrderItem update(OrderItem orderItem) {
    orderItemRepository.update(orderItem);
    return orderItem;
  }

  @Override
  public void delete(long id) {
    orderItemRepository.delete(id);
  }
}

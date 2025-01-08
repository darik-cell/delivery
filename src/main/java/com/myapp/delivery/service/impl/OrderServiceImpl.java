package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.repository.OrderItemRepository;
import com.myapp.delivery.repository.OrderRepository;
import com.myapp.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  @Override
  public Optional<Order> getOrderByOrderId(long id) {
    return orderRepository.findOrderById(id);
  }

  @Override
  public List<Order> getAllUserOrdersByUserId(long id) {
    List<Order> orders = orderRepository.findOrdersByCustomerId(id);
    return orders;
  }

  @Override
  public List<Order> getAllActualUserOrdersByUserId(long id) {
    List<Order> orders = orderRepository.findActualOrdersByCustomerId(id);
    return orders;
  }

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAllOrders();
  }

  @Override
  public boolean setOrderStatus(long id, String status) {
    orderRepository.setStatus(id, status);
    Optional<Order> order = orderRepository.findOrderById(id);
    return order.filter(o -> o.getStatus().getDbValue().equals(status)).isPresent();
  }

  public boolean setCourier(Long id, Long courierId) {
    orderRepository.setCourier(id, courierId);
    Optional<Order> order = orderRepository.findOrderById(id);
    return order.filter(o -> o.getCourierId().equals(courierId)).isPresent();
  }

  @Override
  public Order create(Order order) {
    order.setRestaurantId(1);
    orderRepository.create(order);
    for (var orderItem : order.getOrderItems()) {
      orderItem.setOrderId(order.getId());
    }
    orderItemRepository.createOrderItems(order.getOrderItems());
    order = orderRepository.findOrderById(order.getId()).get();
    return order;
  }
}

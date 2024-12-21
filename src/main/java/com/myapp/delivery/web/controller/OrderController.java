package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.service.OrderService;
import com.myapp.delivery.web.dto.courier.CourierDto;
import com.myapp.delivery.web.dto.order.OrderDto;
import com.myapp.delivery.web.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  @PutMapping
  public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
    Order res = orderService.update(orderMapper.toOrder(orderDto));
    return orderMapper.toOrderDto(res);
  }

  @PostMapping
  public OrderDto createOrder(@RequestBody OrderDto orderDto) {
    Order res = orderService.create(orderMapper.toOrder(orderDto));
    return orderMapper.toOrderDto(res);
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderService.delete(id);
  }

  @GetMapping("/{id}")
  public OrderDto getOrder(@PathVariable Long id) {
    Order res = orderService.getOrderByOrderId(id)
            .orElseThrow(() -> new RuntimeException("нет такого order с таким id = " + id));
    return orderMapper.toOrderDto(res);
  }

  @PutMapping("/{id}/prepare")
  public boolean markOrderAsPreparing(@PathVariable Long id) {
    return orderService.setOrderStatus(id, "готовится");
  }

  @GetMapping("/user-actual/{id}")
  public List<OrderDto> getOrders(@PathVariable Long id) {
    List<Order> res = orderService.getAllActualUserOrdersByUserId(id);
    return orderMapper.toOrderDto(res);
  }

  @GetMapping("/user-all/{id}")
  public List<OrderDto> getAllOrders(@PathVariable Long id) {
    List<Order> res = orderService.getAllUserOrdersByUserId(id);
    return orderMapper.toOrderDto(res);
  }
}

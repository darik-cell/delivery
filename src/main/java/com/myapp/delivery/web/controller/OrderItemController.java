package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.order_item.OrderItem;
import com.myapp.delivery.service.OrderItemService;
import com.myapp.delivery.web.dto.order_item.OrderItemDto;
import com.myapp.delivery.web.mapper.OrderItemMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Hidden
@RestController
@RequestMapping("/api/v1/orderitems")
@RequiredArgsConstructor
public class OrderItemController {

  private final OrderItemService orderItemService;
  private final OrderItemMapper orderItemMapper;

  @PutMapping
  public OrderItemDto updateOrderItem(@RequestBody OrderItemDto orderItemDto) {
    OrderItem res = orderItemService.update(orderItemMapper.toOrderItem(orderItemDto));
    return orderItemMapper.toOrderItemDto(res);
  }

  @PostMapping
  public OrderItemDto createOrderItem(@RequestBody OrderItemDto orderItemDto) {
    OrderItem res = orderItemService.create(orderItemMapper.toOrderItem(orderItemDto));
    return orderItemMapper.toOrderItemDto(res);
  }

  @DeleteMapping("/{id}")
  public void deleteOrderItem(@PathVariable Long id) {
    orderItemService.delete(id);
  }

  @GetMapping("/{id}")
  public OrderItemDto getOrderItem(@PathVariable Long id) {
    OrderItem res = orderItemService.getOrderItemById(id)
            .orElseThrow(() -> new RuntimeException("нет такого menu item с таким id = " + id));
    return orderItemMapper.toOrderItemDto(res);
  }

  @GetMapping("/order/{id}")
  public List<OrderItemDto> getOrderItems(@PathVariable Long id) {
    List<OrderItem> res = orderItemService.getOrderItemsByOrderId(id);
    return orderItemMapper.toOrderItemDto(res);
  }

}

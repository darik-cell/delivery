package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.service.OrderService;
import com.myapp.delivery.web.controller.swagger.OrderControllerApi;
import com.myapp.delivery.web.dto.order.OrderDto;
import com.myapp.delivery.web.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController implements OrderControllerApi {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Override
    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        Order res = orderService.create(orderMapper.toOrder(orderDto));
        return orderMapper.toOrderDto(res);
    }

    @Override
    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        Order res = orderService.getOrderByOrderId(id)
                .orElseThrow(() -> new RuntimeException("нет такого order с таким id = " + id));
        return orderMapper.toOrderDto(res);
    }

    @Override
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderMapper.toOrderDto(orderService.getAllOrders());
    }

    @Override
    @PutMapping("/{id}/prepare")
    public boolean markOrderAsPreparing(@PathVariable Long id) {
        return orderService.setOrderStatus(id, "готовится");
    }

    @Override
    @PutMapping("/{id}/cancel")
    public boolean markOrderAsCanceled(@PathVariable Long id) {
        return orderService.setOrderStatus(id, "отменен");
    }

    @Override
    @GetMapping("/user-actual/{id}")
    public List<OrderDto> getUserAllActualOrders(@PathVariable Long id) {
        List<Order> res = orderService.getAllActualUserOrdersByUserId(id);
        return orderMapper.toOrderDto(res);
    }

    @Override
    @GetMapping("/user-all/{id}")
    public List<OrderDto> getAllOrdersForUser(@PathVariable Long id) {
        List<Order> res = orderService.getAllUserOrdersByUserId(id);
        return orderMapper.toOrderDto(res);
    }
}
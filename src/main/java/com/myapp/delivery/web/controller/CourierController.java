package com.myapp.delivery.web.controller;

import com.myapp.delivery.service.CourierService;
import com.myapp.delivery.web.controller.swagger.CourierControllerApi;
import com.myapp.delivery.web.dto.courier.CourierDto;
import com.myapp.delivery.web.dto.order.OrderDto;
import com.myapp.delivery.web.mapper.CourierMapper;
import com.myapp.delivery.web.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/couriers")
public class CourierController implements CourierControllerApi {

  private final CourierService courierService;
  private final CourierMapper courierMapper;
  private final OrderMapper orderMapper;

  @GetMapping("/all-on-shift")
  public List<CourierDto> getAllOnShift() {
    return courierMapper.toDto(courierService.getAllCouriersOnShift());
  }

  @GetMapping("/{courierId}")
  public CourierDto getById(@PathVariable Long courierId) {
    return courierMapper.toDto(courierService.getCourierById(courierId));
  }

  @GetMapping("/all-on-shift-and-not-on-delivery")
  public List<CourierDto> getAllNotOnDelivery() {
    return courierMapper.toDto(courierService.getAllCouriersNotOnDelivery());
  }

  @GetMapping("/{courierId}/all-actual-orders")
  public List<OrderDto> getActual(@PathVariable Long courierId) {
    return orderMapper.toOrderDto(courierService.getCourierActualOrders(courierId));
  }

  @GetMapping("/{courierId}/all-on-the-way-orders")
  public List<OrderDto> getOnTheWay(@PathVariable Long courierId) {
    return orderMapper.toOrderDto(courierService.getCourierOnTheWayOrders(courierId));
  }

  @PutMapping("/{courierId}/end-order")
  public boolean endOrder(@PathVariable Long courierId, @RequestBody OrderDto orderDto) {
    return courierService.endOrder(courierId, orderDto.getId());
  }

  @PutMapping("/{courierId}/end-delivery")
  public boolean endDelivery(@PathVariable Long courierId) {
    return courierService.endDelivery(courierId);
  }

  @PutMapping("/{courierId}/end-shift")
  public boolean endShift(@PathVariable Long courierId) {
    return courierService.endShift(courierId);
  }

  @PutMapping("/{courierId}/start-shift")
  public boolean startShift(@PathVariable Long courierId) {
    return courierService.goShift(courierId);
  }
}

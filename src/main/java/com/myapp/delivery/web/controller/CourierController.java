package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.courier.Courier;
import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.service.CourierService;
import com.myapp.delivery.web.dto.courier.CourierDto;
import com.myapp.delivery.web.dto.order.OrderDto;
import com.myapp.delivery.web.mapper.CourierMapper;
import com.myapp.delivery.web.mapper.OrderMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Tag(name = "Курьеры-контроллер", description = "Получение курьеров на смене, на доставке и т.д., завершение заказов, завершение доставок, начало смены, текущие заказы курьера")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/couriers")
public class CourierController {

  private final CourierService courierService;
  private final CourierMapper courierMapper;
  private final OrderMapper orderMapper;

  //Postman
  @GetMapping("/all-on-shift")
  public List<CourierDto> getAllOnShift() {
    return courierMapper.toDto(courierService.getAllCouriersOnShift());
  }

  //Postman
  @GetMapping("/all-on-shift-and-not-on-delivery")
  public List<CourierDto> getAllNotOnDelivery() {
    return courierMapper.toDto(courierService.getAllCouriersNotOnDelivery());
  }

  //Postman
  @GetMapping("/{courierId}/all-actual-orders")
  public List<OrderDto> getActual(@PathVariable Long courierId) {
    return orderMapper.toOrderDto(courierService.getCourierActualOrders(courierId));
  }

  //Postman
  @PutMapping("/{courierId}/take-order")
  public boolean takeOrder(@PathVariable Long courierId, @RequestBody OrderDto orderDto) {
    return courierService.takeOrderToCourier(orderDto.getId(), courierId);
  }

  //Postman
  @PutMapping("/{courierId}/go-delivery")
  public boolean goDelivery(@PathVariable Long courierId) {
    return courierService.goDelivery(courierId);
  }

  //Postman
  @PutMapping("/{courierId}/end-order")
  public boolean endOrder(@PathVariable Long courierId, @RequestBody OrderDto orderDto) {
    return courierService.endOrder(courierId, orderDto.getId());
  }

  @PutMapping("/{courierId}/end-delivery")
  public boolean endDelivery(@PathVariable Long courierId) {
    return courierService.endDelivery(courierId);
  }
}

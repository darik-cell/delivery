package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.courier.Courier;
import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.service.CourierService;
import com.myapp.delivery.web.dto.courier.CourierDto;
import com.myapp.delivery.web.dto.order.OrderDto;
import com.myapp.delivery.web.mapper.CourierMapper;
import com.myapp.delivery.web.mapper.OrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Курьеры")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/couriers")
public class CourierController {

  private final CourierService courierService;
  private final CourierMapper courierMapper;
  private final OrderMapper orderMapper;

  @Operation(
          summary = "Получить всех курьеров на смене"
  )
  @GetMapping("/all-on-shift")
  public List<CourierDto> getAllOnShift() {
    return courierMapper.toDto(courierService.getAllCouriersOnShift());
  }

  @Operation(
          summary = "Получить всех курьеров, которым можно назначить доставку"
  )
  @GetMapping("/all-on-shift-and-not-on-delivery")
  public List<CourierDto> getAllNotOnDelivery() {
    return courierMapper.toDto(courierService.getAllCouriersNotOnDelivery());
  }

  @Operation(
          summary = "Получить все заказы для курьера, которые надо доставить"
  )
  @GetMapping("/{courierId}/all-actual-orders")
  public List<OrderDto> getActual(@PathVariable @Parameter(description = "id курьера") Long courierId) {
    return orderMapper.toOrderDto(courierService.getCourierActualOrders(courierId));
  }

//  @PutMapping("/{courierId}/take-order")
//  public boolean takeOrder(@PathVariable Long courierId, @RequestBody OrderDto orderDto) {
//    return courierService.takeOrderToCourier(orderDto.getId(), courierId);
//  }

//  @PutMapping("/{courierId}/go-delivery")
//  public boolean goDelivery(@PathVariable Long courierId) {
//    return courierService.goDelivery(courierId);
//  }

  @Operation(
          summary = "Закончить доставку данного заказа",
          description = "Для тела запроса нужен только id"
  )
  @PutMapping("/{courierId}/end-order")
  public boolean endOrder(@PathVariable @Parameter(description = "id курьера") Long courierId, @RequestBody OrderDto orderDto) {
    return courierService.endOrder(courierId, orderDto.getId());
  }

  @Operation(
          summary = "Закончить доставку для курьера, чтобы можно было брать заказы дальше"
  )
  @PutMapping("/{courierId}/end-delivery")
  public boolean endDelivery(@PathVariable @Parameter(description = "id курьера") Long courierId) {
    return courierService.endDelivery(courierId);
  }
}

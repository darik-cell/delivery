package com.myapp.delivery.web.controller;

import com.myapp.delivery.service.CourierService;
import com.myapp.delivery.web.controller.swagger.ManagerControllerApi;
import com.myapp.delivery.web.dto.order.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
public class ManagerController implements ManagerControllerApi {

  private final CourierService courierService;

  @PutMapping("/{courierId}/take-order")
  public boolean takeOrder(@PathVariable Long courierId, @RequestBody OrderDto orderDto) {
    return courierService.takeOrderToCourier(orderDto.getId(), courierId);
  }

  @PutMapping("/{courierId}/go-delivery")
  public boolean goDelivery(@PathVariable Long courierId) {
    return courierService.goDelivery(courierId);
  }
}

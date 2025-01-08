package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.web.dto.courier.CourierDto;
import com.myapp.delivery.web.dto.order.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Курьеры")
public interface CourierControllerApi {

  @Operation(
          summary = "Получить всех курьеров на смене"
  )
  public List<CourierDto> getAllOnShift();

  @Operation(
          summary = "Получить всех курьеров, которым можно назначить доставку"
  )
  public List<CourierDto> getAllNotOnDelivery();

  @Operation(
          summary = "Получить все заказы для курьера, которые надо доставить"
  )
  public List<OrderDto> getActual(@Parameter(description = "id курьера") Long courierId);

  @Operation(
          summary = "Закончить доставку данного заказа",
          description = "Для тела запроса нужен только id"
  )
  public boolean endOrder(@Parameter(description = "id курьера") Long courierId, OrderDto orderDto);

  @Operation(
          summary = "Закончить доставку для курьера, чтобы можно было брать заказы дальше"
  )
  public boolean endDelivery(@Parameter(description = "id курьера") Long courierId);

  @Operation(
          summary = "Получить данные курьера по id"
  )
  public CourierDto getById(@Parameter(description = "id курьера") Long courierId);
}

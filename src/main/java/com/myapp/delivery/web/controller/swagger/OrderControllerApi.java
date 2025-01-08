package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.web.dto.order.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Заказы")
public interface OrderControllerApi {

  @Operation(
          summary = "Создать заказ",
          requestBody = @RequestBody(
                  content = @io.swagger.v3.oas.annotations.media.Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  value = """
                                            {
                                                "customerId": 1,
                                                "totalPrice": 1000,
                                                "paymentMethod": "Карта",
                                                "paymentStatus": "Не оплачено",
                                                "orderItems": [
                                                    {
                                                        "menuItem": {
                                                            "id": 1
                                                        },
                                                        "quantity": 2,
                                                        "priceAtOrderTime": 100
                                                    },
                                                    {
                                                        "menuItem": {
                                                            "id": 2
                                                        },
                                                        "quantity": 10,
                                                        "priceAtOrderTime": 100
                                                    }
                                                ],
                                                "deliveryAddress": "улица Гениев"
                                            }
                                            """
                          )
                  )
          )
  )
  OrderDto createOrder(OrderDto orderDto);

  @Operation(
          summary = "Получить заказ по id заказа"
  )
  OrderDto getOrder(@Parameter(description = "id заказа") Long id);

  @Operation(
          summary = "Получить все заказы"
  )
  List<OrderDto> getAllOrders();

  @Operation(
          summary = "По id заказа изменить статус заказа на 'готовится'"
  )
  boolean markOrderAsPreparing(@Parameter(description = "id заказа") Long id);

  @Operation(
          summary = "По id заказа изменить статус заказа на 'отменен'"
  )
  boolean markOrderAsCanceled(@Parameter(description = "id заказа") Long id);

  @Operation(
          summary = "Получить все актуальные (которые клиент ждет) заказы по id клиента"
  )
  List<OrderDto> getUserAllActualOrders(@Parameter(description = "id клиента") Long id);

  @Operation(
          summary = "Получить вообще все заказы (которые отменены и доставлены тоже)"
  )
  List<OrderDto> getAllOrdersForUser(@Parameter(description = "id клиента") Long id);
}

package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.web.dto.order.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Менеджер")
public interface ManagerControllerApi {

  @Operation(
          summary = "Дать заказ курьеру",
          requestBody = @RequestBody(
                  content = @Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  name = "Заказ",
                                  summary = "Надо передать только id заказа",
                                  value = """
                                          {
                                              "id": 1
                                          }
                                          """
                          )
                  )
          )
  )
  public boolean takeOrder(@Parameter(description = "id курьера") Long courierId,
                           OrderDto orderDto);

  @Operation(
          summary = "Отправить курьера на доставку"
  )
  public boolean goDelivery(@Parameter(description = "id курьера") Long courierId);
}

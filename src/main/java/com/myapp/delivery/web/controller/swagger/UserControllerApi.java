package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.web.dto.user.UserDto;
import com.myapp.delivery.web.dto.user.UserWithOrdersDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Пользователь")
public interface UserControllerApi {

  @Operation(
          summary = "Получить клиента по id, без заказов"
  )
  UserDto getUserWithoutOrdersById(
          @Parameter(description = "id клиента") Long id
  );

  @Operation(
          summary = "Получить всех клиентов без их заказов"
  )
  List<UserDto> getAllUsersWithoutOrders();


  @Operation(
          summary = "Получить всех клиентов (customer)"
  )
  public List<UserDto> getAllCustomersWithoutOrders();

  @Operation(
          summary = "Получить всех курьеров"
  )
  public List<UserDto> getAllCouriersWithoutOrders();

  @Operation(
          summary = "Получить всех менеджеров"
  )
  public List<UserDto> getAllManagersWithoutOrders();

  @Operation(
          summary = "Обновить клиента",
          description = "Можно передать без пароля, либо с паролем и подтверждением пароля, в любом случае отработает",
          requestBody = @RequestBody(
                  content = @io.swagger.v3.oas.annotations.media.Content(
                          mediaType = "application/json",
                          examples = {
                                  @ExampleObject(
                                          name = "с паролем",
                                          summary = "С паролем",
                                          value = """
                                                    {
                                                        "name": "Паша Пашевич",
                                                        "username": "customer",
                                                        "phone": "79994445566",
                                                        "address": "Адрес клиента",
                                                        "password": "password",
                                                        "passwordConfirmation": "password"
                                                    }
                                                    """
                                  ),
                                  @ExampleObject(
                                          name = "без пароля",
                                          summary = "Без пароля",
                                          value = """
                                                    {
                                                        "name": "Паша Пашевич",
                                                        "username": "customer",
                                                        "phone": "79994445566",
                                                        "address": "Адрес клиента"
                                                    }
                                                    """
                                  )
                          }
                  )
          )
  )
  UserDto updateUserById(Long id, UserDto userDto);

  @Operation(
          summary = "По id клиента получить клиента со всеми его заказами"
  )
  UserWithOrdersDto findUserWithOrdersById(
          @Parameter(description = "id клиента") Long id
  );

  @Operation(
          summary = "По id клиента получить клиента со всеми его активными заказами (которые он ждет)"
  )
  UserWithOrdersDto findUserWithActualOrdersById(
          @Parameter(description = "id клиента") Long id
  );

  @Operation(
          summary = "Удалить клиента по id"
  )
  void deleteUserById(
          @Parameter(description = "id клиента") Long id
  );
}

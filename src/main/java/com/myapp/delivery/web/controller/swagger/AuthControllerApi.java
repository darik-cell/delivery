package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.web.dto.auth.JwtRequest;
import com.myapp.delivery.web.dto.auth.JwtResponse;
import com.myapp.delivery.web.dto.auth.RefreshTokenRequest;
import com.myapp.delivery.web.dto.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth-controller")
public interface AuthControllerApi {

  @Operation(
          summary = "Аутентификация (логин)",
          description = "Выдает токены если валидные логин и пароль"
  )
  public ResponseEntity<?> login(JwtRequest loginRequest);

  @Operation(
          summary = "Регистрация",
          description = "Добавляет пользователя в базу данных",
          requestBody = @RequestBody(
                  content = @Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  value = """
                                          {
                                            "name": "user_registered",
                                            "username": "user_username",
                                            "password": "password",
                                            "passwordConfirmation": "password",
                                            "phone": "89990007766",
                                            "address": "address"
                                          }
                                          """
                          )
                  )
          )
  )
  public UserDto register(UserDto userDto);

  @Operation(
          summary = "По рефреш токену выдает оба токена, если рефреш валидный"
  )
  public JwtResponse refresh(RefreshTokenRequest refreshToken);

  @Operation(
          summary = "Регистрация курьера или менеджера",
          description = "Добавляет курьера или менеджера в базу данных, в roles надо указать ROLE_MANAGER или ROLE_COURIER",
          requestBody = @RequestBody(
                  content = @Content(
                          mediaType = "application/json",
                          examples = {
                                  @ExampleObject(
                                          name = "Курьер",
                                          summary = "Курьер",
                                          value = """
                                                  {
                                                    "name": "courier_registered",
                                                    "username": "courier_username",
                                                    "password": "password",
                                                    "passwordConfirmation": "password",
                                                    "phone": "89990007766",
                                                    "address": "address",
                                                    "roles": [
                                                      "ROLE_COURIER"
                                                    ]
                                                  }
                                                  """
                                  ),
                                  @ExampleObject(
                                          name = "Менеджер",
                                          summary = "Менеджер",
                                          value = """
                                                  {
                                                    "name": "manager_registered",
                                                    "username": "manager_username",
                                                    "password": "password",
                                                    "passwordConfirmation": "password",
                                                    "phone": "89990007766",
                                                    "address": "address",
                                                    "roles": [
                                                      "ROLE_MANAGER"
                                                    ]
                                                  }
                                                  """
                                  )
                          }
                  )
          )
  )
  public UserDto registerForAdmin(UserDto userDto);
}

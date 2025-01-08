package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.web.dto.auth.JwtRequest;
import com.myapp.delivery.web.dto.auth.JwtResponse;
import com.myapp.delivery.web.dto.auth.RefreshTokenRequest;
import com.myapp.delivery.web.dto.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth-controller")
public interface AuthControllerApi {

  @Operation(
          summary = "Аутентификация (логин)",
          description = "Выдает токены если валидные логин и пароль"
  )
  public JwtResponse login(JwtRequest loginRequest);

  @Operation(
          summary = "Регистрация",
          description = "Добавляет пользователя в базу данных"
  )
  public UserDto register(UserDto userDto);

  @Operation(
          summary = "По рефреш токену выдает оба токена, если рефреш валидный"
  )
  public JwtResponse refresh(RefreshTokenRequest refreshToken);
}

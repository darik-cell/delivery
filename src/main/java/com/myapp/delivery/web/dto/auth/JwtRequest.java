package com.myapp.delivery.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {

  @Schema(description = "Почта пользователя", example = "some@mail.com")
  @NotNull(message = "Username must be not null.")
  private String username;

  @Schema(description = "Пароль пользователя", example = "password1234")
  @NotNull(message = "Password must be not null.")
  private String password;
}

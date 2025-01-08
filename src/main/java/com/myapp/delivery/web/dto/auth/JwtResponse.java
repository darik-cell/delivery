package com.myapp.delivery.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JwtResponse {

  @Schema(description = "Уникальный идентификатор", example = "1")
  private Long id;

  @Schema(description = "Электронная почта", example = "some@mail.com")
  private String username;

  @Schema(description = "Аксес токен", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lciIsImlkIjo0LCJyb2xlcyI6WyJST0xFX0NVU1RPTUVSIl0sImlhdCI6MTczNjI4MjQ4OCwiZXhwIjoxNzM2Mjg2MDg4fQ.KCBjhuPZT8A__dztSPVybJKcJ8BNyeJtS8SMsIGN_2M")
  private String accessToken;

  @Schema(description = "Рефреш токен", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lciIsImlkIjo0LCJpYXQiOjE3MzYyODI0ODgsImV4cCI6MTczODg3NDQ4OH0.pLJEIYCTzY111RZ5gXLvGGy3QZQpAfcuvcO4xqmLNAA")
  private String refreshToken;
}

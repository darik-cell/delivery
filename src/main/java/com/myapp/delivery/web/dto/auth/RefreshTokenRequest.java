package com.myapp.delivery.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RefreshTokenRequest {

  @Schema(description = "Рефреш токен", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lciIsImlkIjo0LCJpYXQiOjE3MzYyODI0ODgsImV4cCI6MTczODg3NDQ4OH0.pLJEIYCTzY111RZ5gXLvGGy3QZQpAfcuvcO4xqmLNAA")
  private String refreshToken;
}

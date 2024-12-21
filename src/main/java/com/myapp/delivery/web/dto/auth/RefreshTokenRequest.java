package com.myapp.delivery.web.dto.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
  private String refreshToken;
}

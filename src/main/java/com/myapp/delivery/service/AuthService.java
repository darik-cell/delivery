package com.myapp.delivery.service;

import com.myapp.delivery.web.dto.auth.JwtRequest;
import com.myapp.delivery.web.dto.auth.JwtResponse;
import com.myapp.delivery.web.dto.auth.RefreshTokenRequest;


public interface AuthService {

  JwtResponse login(JwtRequest loginRequest);

  JwtResponse refresh(RefreshTokenRequest refreshToken);
}

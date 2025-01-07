package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.service.AuthService;
import com.myapp.delivery.service.UserService;
import com.myapp.delivery.web.dto.auth.JwtRequest;
import com.myapp.delivery.web.dto.auth.JwtResponse;
import com.myapp.delivery.web.dto.auth.RefreshTokenRequest;
import com.myapp.delivery.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JwtTokenProvider jwtTokenProvider;
  private final Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());

  @Override
  public JwtResponse login(JwtRequest loginRequest) {
    JwtResponse jwtResponse = new JwtResponse();
    logger.info("loginRequest: " + loginRequest);
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    User user = userService.getWithoutOrdersByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new RuntimeException("User with username " + loginRequest.getUsername() + " not found"));
    jwtResponse.setId(user.getId());
    jwtResponse.setUsername(user.getUsername());
    jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles()));
    jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));
    return jwtResponse;
  }

  @Override
  public JwtResponse refresh(RefreshTokenRequest refreshToken) {
    return jwtTokenProvider.refreshUserTokens(refreshToken.getRefreshToken());
  }
}

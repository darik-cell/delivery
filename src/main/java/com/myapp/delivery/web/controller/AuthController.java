package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.service.AuthService;
import com.myapp.delivery.service.UserService;
import com.myapp.delivery.web.dto.auth.JwtRequest;
import com.myapp.delivery.web.dto.auth.JwtResponse;
import com.myapp.delivery.web.dto.auth.RefreshTokenRequest;
import com.myapp.delivery.web.dto.user.UserDto;
import com.myapp.delivery.web.dto.validation.OnCreate;
import com.myapp.delivery.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

  private final AuthService authService;
  private final UserService userService;
  private final Logger logger = Logger.getLogger(AuthController.class.getName());

  private final UserMapper userMapper;

  @PostMapping("/login")
  public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
    return authService.login(loginRequest);
  }

  @PostMapping("/register")
  public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
    User user = userMapper.toUser(userDto);
    User createdUser = userService.createCustomer(user);
    return userMapper.fromUserToUserDto(createdUser);
  }

  @PostMapping("/refresh")
  public JwtResponse refresh(@RequestBody RefreshTokenRequest refreshToken) {
    return authService.refresh(refreshToken);
  }
}

package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.user.Role;
import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.service.AuthService;
import com.myapp.delivery.service.UserService;
import com.myapp.delivery.web.controller.swagger.AuthControllerApi;
import com.myapp.delivery.web.dto.auth.JwtRequest;
import com.myapp.delivery.web.dto.auth.JwtResponse;
import com.myapp.delivery.web.dto.auth.RefreshTokenRequest;
import com.myapp.delivery.web.dto.user.UserDto;
import com.myapp.delivery.web.dto.validation.OnCreate;
import com.myapp.delivery.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController implements AuthControllerApi {

  private final AuthService authService;
  private final UserService userService;
  private final Logger logger = Logger.getLogger(AuthController.class.getName());

  private final UserMapper userMapper;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Validated @RequestBody JwtRequest loginRequest) {
    JwtResponse res;
    try {
       res = authService.login(loginRequest);
    } catch (InvalidParameterException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный логин");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный пароль");
    }
    return ResponseEntity.accepted().body(res);
  }

  @PostMapping("/register")
  public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
    User user = userMapper.toUser(userDto);
    User createdUser = userService.create(user, Role.ROLE_CUSTOMER);
    return userMapper.fromUserToUserDto(createdUser);
  }

  @PostMapping("/register-for-admin")
  public UserDto registerForAdmin(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
    User user = userMapper.toUser(userDto);
    User createdUser = userService.create(user, userDto.getRoles().iterator().next());
    return userMapper.fromUserToUserDto(createdUser);
  }

  @PostMapping("/refresh")
  public JwtResponse refresh(@RequestBody RefreshTokenRequest refreshToken) {
    return authService.refresh(refreshToken);
  }
}

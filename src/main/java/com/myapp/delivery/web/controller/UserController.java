package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.repository.UserRepository;
import com.myapp.delivery.service.UserService;
import com.myapp.delivery.web.dto.user.UserDto;
import com.myapp.delivery.web.dto.user.UserWithOrdersDto;
import com.myapp.delivery.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping("/{id}/no-orders")
  public UserDto getUserWithoutOrdersById (@PathVariable Long id) {
    return userMapper.fromUserToUserDto(userService.getWithoutOrdersById(id).orElseThrow(() ->
            new RuntimeException("user with id " + id + " not found")));
  }

  @GetMapping
  public List<UserDto> getAllUsersWithoutOrders() {
    return userMapper.fromUserToUserDto(userService.getAllWithoutOrders());
  }

  @PutMapping("/{id}")
  public UserDto updateUserById (@PathVariable Long id, @RequestBody UserDto userDto) {
    User newUser;
    if (null == userDto.getPassword()) {
      newUser = userService.updateCustomerWithoutPassword(userMapper.toUser(userDto));
    } else {
      newUser = userService.updateCustomerWithPassword(userMapper.toUser(userDto));
    }
    return userMapper.fromUserToUserDto(newUser);
  }

  @GetMapping("/{id}/with-all-orders")
  public UserWithOrdersDto findUserWithOrdersById (@PathVariable Long id) {
    User user = userService.getWithOrdersById(id).orElseThrow(() ->
            new RuntimeException("Пользователь с id " + id + " не найден"));
    return userMapper.toUserWithOrdersDto(user);
  }

  @GetMapping("/{id}/with-actual-orders")
  public UserWithOrdersDto findUserWithActualOrdersById (@PathVariable Long id) {
    User user = userService.getWithActualOrdersById(id).orElseThrow(() ->
            new RuntimeException("Пользователь с id " + id + " не найден"));
    return userMapper.toUserWithOrdersDto(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUserById (@PathVariable Long id) {
    userService.delete(id);
  }
}

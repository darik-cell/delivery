package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.service.UserService;
import com.myapp.delivery.web.controller.swagger.UserControllerApi;
import com.myapp.delivery.web.dto.user.UserDto;
import com.myapp.delivery.web.dto.user.UserWithOrdersDto;
import com.myapp.delivery.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController implements UserControllerApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @GetMapping("/{id}/no-orders")
    public UserDto getUserWithoutOrdersById(
            @PathVariable Long id
    ) {
        return userMapper.fromUserToUserDto(
                userService.getWithoutOrdersById(id).orElseThrow(() ->
                        new RuntimeException("user with id " + id + " not found"))
        );
    }

    @Override
    @GetMapping
    public List<UserDto> getAllUsersWithoutOrders() {
        return userMapper.fromUserToUserDto(userService.getAllWithoutOrders());
    }

    @GetMapping("/customers")
    public List<UserDto> getAllCustomersWithoutOrders() {
        return userMapper.fromUserToUserDto(userService.getAllCustomersWithoutOrders());
    }

    @GetMapping("/couriers")
    public List<UserDto> getAllCouriersWithoutOrders() {
        return userMapper.fromUserToUserDto(userService.getAllCouriersWithoutOrders());
    }

    @GetMapping("/managers")
    public List<UserDto> getAllManagersWithoutOrders() {
        return userMapper.fromUserToUserDto(userService.getAllManagersWithoutOrders());
    }

    @Override
    @PutMapping("/{id}")
    public UserDto updateUserById(
            @PathVariable Long id,
            @RequestBody UserDto userDto
    ) {
        userDto.setId(id);
        User newUser;
        if (userDto.getPassword() == null) {
            newUser = userService.updateCustomerWithoutPassword(userMapper.toUser(userDto));
        } else {
            newUser = userService.updateCustomerWithPassword(userMapper.toUser(userDto));
        }
        return userMapper.fromUserToUserDto(newUser);
    }

    @Override
    @GetMapping("/{id}/with-all-orders")
    public UserWithOrdersDto findUserWithOrdersById(
            @PathVariable Long id
    ) {
        User user = userService.getWithOrdersById(id).orElseThrow(() ->
                new RuntimeException("Пользователь с id " + id + " не найден"));
        return userMapper.toUserWithOrdersDto(user);
    }

    @Override
    @GetMapping("/{id}/with-actual-orders")
    public UserWithOrdersDto findUserWithActualOrdersById(
            @PathVariable Long id
    ) {
        User user = userService.getWithActualOrdersById(id).orElseThrow(() ->
                new RuntimeException("Пользователь с id " + id + " не найден"));
        return userMapper.toUserWithOrdersDto(user);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteUserById(
            @PathVariable Long id
    ) {
        userService.delete(id);
    }
}

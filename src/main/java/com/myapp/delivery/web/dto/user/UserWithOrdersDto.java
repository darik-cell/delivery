package com.myapp.delivery.web.dto.user;

import com.myapp.delivery.web.dto.order.OrderDto;
import lombok.Data;

import java.util.List;

@Data
public class UserWithOrdersDto {

  private Long id;

  private String name;

  private String username;

  private String password;

  private String phone;

  private String address;

  private List<OrderDto> orders;
}

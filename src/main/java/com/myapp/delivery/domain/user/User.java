package com.myapp.delivery.domain.user;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.domain.order.Order;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@ToString
public class User {
  private Long id;
  private String name;
  private String username;
  private String password;
  private String passwordConfirmation;
  private String phone;
  private Set<Role> roles;
  private List<Order> orders;
  private String address;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}

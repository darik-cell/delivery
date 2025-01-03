package com.myapp.delivery.service;

import com.myapp.delivery.domain.user.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService {
  Optional<User> getWithOrdersById(long id);
  Optional<User> getWithoutOrdersById(long id);

  Optional<User> getWithoutOrdersByUsername(String username);

  User update(User user);

  User create(User user);

  void delete(long id);
}

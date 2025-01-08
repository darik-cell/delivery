package com.myapp.delivery.service;

import com.myapp.delivery.domain.user.Role;
import com.myapp.delivery.domain.user.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserService {
  Optional<User> getWithOrdersById(long id);
  Optional<User> getWithoutOrdersById(long id);
  Optional<User> getWithActualOrdersById(long id);
  List<User> getAllWithoutOrders();
  List<User> getAllCustomersWithoutOrders();
  List<User> getAllCouriersWithoutOrders();
  List<User> getAllManagersWithoutOrders();

  Optional<User> getWithoutOrdersByUsername(String username);

  User updateCustomerWithoutPassword(User user);
  User updateCustomerWithPassword(User user);

  User create(User user, Role role);

  void delete(long id);
}

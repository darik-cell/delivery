package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.user.Role;
import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.repository.UserRepository;
import com.myapp.delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

  @Override
  public Optional<User> getWithOrdersById(long id) {
    return userRepository.findWithOrdersById(id);
  }

  @Override
  public Optional<User> getWithoutOrdersById(long id) {
    return userRepository.findWithoutOrdersById(id);
  }

  @Override
  public Optional<User> getWithoutOrdersByUsername(String username) {
    return userRepository.findWithoutOrdersByUsername(username);
  }

  @Override
  public User update(User user) {
    return null;
  }

  @Override
  public User create(User user) {
    logger.info("В начале метода create(): " + user.toString());
    if (userRepository.findWithoutOrdersByUsername(user.getUsername()).isPresent()) {
      throw new IllegalStateException("User already exists.");
    }
    if (!user.getPassword().equals(user.getPasswordConfirmation())) {
      throw new IllegalStateException("Passwords and password confirmation do not match.");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.create(user);
    logger.info("После вызова userRepository.create(user): " + user);
    Set<Role> roles = Set.of(Role.ROLE_CUSTOMER);
    userRepository.insertUserRole(user.getId(), Role.ROLE_CUSTOMER);
    user.setRoles(roles);
    return user;
  }

  @Override
  public void delete(long id) {
    userRepository.delete(id);
  }
}

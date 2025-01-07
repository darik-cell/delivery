package com.myapp.delivery.repository;

import com.myapp.delivery.domain.user.Role;
import com.myapp.delivery.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {
  Optional<User> findWithOrdersById(Long id);
  Optional<User> findWithoutOrdersById(Long id);
  Optional<User> findWithActualOrdersById(Long id);
  List<User> findAllWithoutOrders();

  Optional<User> findWithoutOrdersByUsername(String username);

  void updateWithoutPassword(User user);
  void updateWithPassword(User user);

  void create(User user);

  void insertUserRole(@Param("userId") long userId, @Param("role") Role role);

  void delete(long id);
}

package com.myapp.delivery.repository;

import com.myapp.delivery.domain.order.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderRepository {

  List<Order> findOrdersByCustomerId(Long customerId);
  Optional<Order> findOrderById(Long id);
  List<Order> findActualOrdersByCustomerId(Long customerId);
  List<Order> findOrdersWithNoCourier();
  List<Order> findActualOrdersByCourierId(Long courierId);
  List<Order> findOnTheWayOrdersByCourierId(Long courierId);

  void setStatus(@Param("id") Long id, @Param("status") String status);

  void setPaymentStatusPayed(Long id);

  void setDeliveryTime(@Param("id") Long id, @Param("deliveryTime") Timestamp deliveryTime);

  void setCourier(@Param("id") long id, @Param("courierId") long courierId);

  void create(Order order);
  void update(Order order);
  void delete(long id);
}

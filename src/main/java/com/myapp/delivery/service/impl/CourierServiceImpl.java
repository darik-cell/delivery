package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.courier.Courier;
import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.domain.order.PaymentStatus;
import com.myapp.delivery.domain.order.Status;
import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.repository.CourierRepository;
import com.myapp.delivery.repository.OrderRepository;
import com.myapp.delivery.repository.UserRepository;
import com.myapp.delivery.service.CourierService;
import com.myapp.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

  private final CourierRepository courierRepository;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;
  private final OrderService orderService;

  @Override
  public List<Order> getCourierActualOrders(Long courierId) {
    return orderRepository.findActualOrdersByCourierId(courierId);
  }

  @Override
  public List<Courier> getAllCouriersOnShift() {
    List<Courier> couriers = courierRepository.findAllOnShift();
    couriers.forEach(cur -> {
      User user = userRepository.findWithoutOrdersById(cur.getUserId()).get();
      cur.setName(user.getName());
      cur.setPhone(user.getPhone());
    });
    return couriers;
  }

  @Override
  public List<Courier> getAllCouriersNotOnDelivery() {
    List<Courier> couriers = courierRepository.findAllNotOnDelivery();
    couriers.forEach(cur -> {
      User user = userRepository.findWithoutOrdersById(cur.getUserId()).get();
      cur.setName(user.getName());
      cur.setPhone(user.getPhone());
    });
    return couriers;
  }

  @Override
  public boolean takeOrderToCourier(Long orderId, Long courierId) {
    return orderService.setCourier(orderId, courierId)
            && orderService.setOrderStatus(orderId, "назначен курьер");
  }

  @Override
  public boolean goDelivery(Long courierId) {
    List<Order> orders = orderRepository.findActualOrdersByCourierId(courierId);
    int count = orders.size();
    orders = orders.stream().filter(o ->
            orderService.setOrderStatus(o.getId(), "в пути")).toList();
    if (orders.size() != count) {
      throw new RuntimeException("Что-то не так с методом goDelivery");
    }
    courierRepository.setOnDelivery(courierId);
    return true;
  }

  @Override
  public boolean endOrder(Long courierId, Long orderId) {
    Optional<Order> order = orderRepository.findOrderById(orderId);
    if (order.filter(o -> o.getStatus().equals(Status.ON_THE_WAY)).isEmpty()) {
      return false;
    }
    Timestamp now = new Timestamp(System.currentTimeMillis());
    orderRepository.setDeliveryTime(orderId, now);
    orderRepository.setStatus(orderId, "доставлен");
    orderRepository.setPaymentStatusPayed(orderId);

    Optional<Order> newOrder = orderRepository.findOrderById(orderId);

    return newOrder.filter(o ->
            o.getStatus().getDbValue().equals("доставлен") &&
            o.getDeliveryTime().equals(now) &&
            o.getPaymentStatus().equals(PaymentStatus.PAID)).isPresent();
  }

  @Override
  public boolean endDelivery(Long courierId) {
    List<Order> orders = orderRepository.findOnTheWayOrdersByCourierId(courierId);
    if (!orders.isEmpty()) {
      return false;
    }
    courierRepository.setNotOnDelivery(courierId);
    Optional<Courier> courier = courierRepository.findById(courierId);
    return courier.filter(c -> !c.isOnDelivery()).isPresent();
  }
}














package com.myapp.delivery.service;

import com.myapp.delivery.domain.courier.Courier;
import com.myapp.delivery.domain.order.Order;

import java.util.List;

public interface CourierService {

  List<Order> getCourierActualOrders(Long courierId);
  List<Courier> getAllCouriersOnShift();
  List<Courier> getAllCouriersNotOnDelivery();

  boolean takeOrderToCourier(Long orderId, Long courierId);
  boolean goDelivery(Long courierId);
  boolean endOrder(Long courierId, Long orderId);
  boolean endDelivery(Long courierId);
}

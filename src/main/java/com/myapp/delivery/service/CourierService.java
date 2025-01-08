package com.myapp.delivery.service;

import com.myapp.delivery.domain.courier.Courier;
import com.myapp.delivery.domain.order.Order;

import java.util.List;

public interface CourierService {

  List<Order> getCourierActualOrders(Long courierId);
  List<Courier> getAllCouriersOnShift();
  List<Courier> getAllCouriersNotOnDelivery();
  public Courier getCourierById(Long courierId);

  boolean takeOrderToCourier(Long orderId, Long courierId);
  boolean endOrder(Long courierId, Long orderId);

  boolean goDelivery(Long courierId);
  boolean endDelivery(Long courierId);


  boolean goShift(Long courierId);
  boolean endShift(Long courierId);
}

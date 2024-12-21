package com.myapp.delivery.repository;

import com.myapp.delivery.domain.courier.Courier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CourierRepository {

  List<Courier> findAll();
  List<Courier> findAllOnShift();
  List<Courier> findAllNotOnDelivery();
  Optional<Courier> findById(Long id);

  void setOnDelivery(long courierId);
  void setNotOnDelivery(long courierId);
  void setOnShift(long courierId);
  void setNotOnShift(long courierId);
}

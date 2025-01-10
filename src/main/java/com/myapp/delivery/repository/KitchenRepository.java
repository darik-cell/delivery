package com.myapp.delivery.repository;

import com.myapp.delivery.domain.kitchen.Kitchen;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
public interface KitchenRepository {
  Optional<Kitchen> findById(Long id);
  List<Kitchen> findAll();

  void insertKitchen(Kitchen kitchen);

  void updateKitchen(Kitchen kitchen);

  void delete(Long id);
}

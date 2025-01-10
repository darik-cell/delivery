package com.myapp.delivery.service;

import com.myapp.delivery.domain.kitchen.Kitchen;

import java.util.List;
import java.util.Optional;

public interface KitchenService {
  Optional<Kitchen> getById(Long id);
  List<Kitchen> getAll();

  Kitchen insertKitchen(Kitchen kitchen);

  Kitchen updateKitchen(Kitchen kitchen);

  void delete(Long id);
}

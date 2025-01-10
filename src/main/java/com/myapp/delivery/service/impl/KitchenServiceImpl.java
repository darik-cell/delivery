package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.kitchen.Kitchen;
import com.myapp.delivery.repository.KitchenRepository;
import com.myapp.delivery.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {

  private final KitchenRepository kitchenRepository;

  @Override
  public Optional<Kitchen> getById(Long id) {
    return kitchenRepository.findById(id);
  }

  @Override
  public List<Kitchen> getAll() {
    return kitchenRepository.findAll();
  }

  @Override
  public Kitchen insertKitchen(Kitchen kitchen) {
    kitchenRepository.insertKitchen(kitchen);
    return kitchen;
  }

  @Override
  public Kitchen updateKitchen(Kitchen kitchen) {
    kitchenRepository.updateKitchen(kitchen);
    kitchen = kitchenRepository.findById(kitchen.getId()).get();
    return kitchen;
  }

  @Override
  public void delete(Long id) {
    kitchenRepository.delete(id);
  }
}

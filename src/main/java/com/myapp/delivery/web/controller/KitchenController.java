package com.myapp.delivery.web.controller;

import com.myapp.delivery.domain.kitchen.Kitchen;
import com.myapp.delivery.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kitchens")
public class KitchenController {

  private final KitchenService kitchenService;

  @PostMapping
  public Kitchen createKitchen(@RequestBody Kitchen kitchen) {
    return kitchenService.insertKitchen(kitchen);
  }

  @GetMapping
  public List<Kitchen> findAll() {
    return kitchenService.getAll();
  }

  @GetMapping("/{id}")
  public Kitchen findById(@PathVariable Long id) {
    return kitchenService.getById(id).get();
  }

  @PutMapping
  public Kitchen update(@RequestBody Kitchen kitchen) {
    return kitchenService.updateKitchen(kitchen);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    kitchenService.delete(id);
  }
}

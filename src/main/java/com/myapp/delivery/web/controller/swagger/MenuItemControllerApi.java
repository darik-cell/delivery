package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.web.dto.menu_item.MenuItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface MenuItemControllerApi {

  @Operation(
          summary = "Обновить блюдо из меню",
          description = "Надо получить список ингридиентов через get запрос на /api/v1/ingredients, для обновления изображения другая конечная точка",
          requestBody = @RequestBody(
                  content = @Content(
                          mediaType = "application/json",
                          examples = {
                                  @ExampleObject(
                                          name = "Кратко",
                                          summary = "Ингридиенты кратко",
                                          value = """
                            {
                                "name": "Борщ",
                                "description": "Классическое блюдо русской кухни",
                                "price": 100,
                                "category": "Суп",
                                "availabilityStatus": "true",
                                "weight": 50,
                                "calories": 500,
                                "ingredients": [
                                    { "id": 1 },
                                    { "id": 2 },
                                    { "id": 3 }
                                ] 
                            }
                            """
                                  ),
                                  @ExampleObject(
                                          name = "Длинно",
                                          summary = "Ингридиенты с названием",
                                          value = """
                            {
                                "name": "Борщ",
                                "description": "Классическое блюдо русской кухни",
                                "price": 100,
                                "category": "Суп",
                                "availabilityStatus": "true",
                                "weight": 50,
                                "calories": 500,
                                "ingredients": [
                                    { "id": 1, "name": "Говядина" },
                                    { "id": 2, "name": "Абрикосы" },
                                    { "id": 3, "name": "Каперсы" }
                                ] 
                            }
                            """
                                  )
                          }
                  )
          )
  )
  MenuItem updateMenuItem(MenuItemDto menuItem);

  @Operation(
          summary = "Создать элемент меню",
          requestBody = @RequestBody(
                  content = @Content(
                          mediaType = "application/json",
                          examples = {
                                  @ExampleObject(
                                          name = "Кратко",
                                          summary = "Ингридиенты кратко",
                                          value = """
                            {
                                "name": "Борщ",
                                "description": "Классическое блюдо русской кухни",
                                "price": 100,
                                "category": "Суп",
                                "availabilityStatus": "true",
                                "weight": 50,
                                "calories": 500,
                                "ingredients": [
                                    { "id": 1 },
                                    { "id": 2 },
                                    { "id": 3 }
                                ] 
                            }
                            """
                                  ),
                                  @ExampleObject(
                                          name = "Длинно",
                                          summary = "Ингридиенты с названием",
                                          value = """
                            {
                                "name": "Борщ",
                                "description": "Классическое блюдо русской кухни",
                                "price": 100,
                                "category": "Суп",
                                "availabilityStatus": "true",
                                "weight": 50,
                                "calories": 500,
                                "ingredients": [
                                    { "id": 1, "name": "Говядина" },
                                    { "id": 2, "name": "Абрикосы" },
                                    { "id": 3, "name": "Каперсы" }
                                ] 
                            }
                            """
                                  )
                          }
                  )
          )
  )
  MenuItemDto createMenuItem(MenuItemDto menuItemDto);
}

package com.myapp.delivery.web.controller.swagger;

import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.web.dto.menu_item.MenuItemDto;
import com.myapp.delivery.web.dto.menu_item.MenuItemImageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Tag(name = "Меню")
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

  @Operation(
          summary = "Удалить элемент меню"
  )
  public void deleteMenuItem(@Parameter(description = "id элемента меню") Long id);

  @Operation(
          summary = "Получить элемент меню по id"
  )
  public MenuItemDto getMenuItem(@Parameter(description = "id элемента меню") Long id);

  @Operation(
          summary = "Получить все элементы меню"
  )
  public List<MenuItemDto> getMenuItems();

  @Operation(
          summary = "Загрузить изображение для элемента меню"
  )
  public void uploadImage(@Parameter(description = "id элемента меню") Long id,
                          MenuItemImageDto imageDto);

  @Operation(
          summary = "Получить изображение для элемента меню"
  )
  public ResponseEntity<byte[]> getImage(@Parameter(description = "id элемента меню") Long id);

}

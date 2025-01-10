package com.myapp.delivery.web.dto.menu_item;

import com.myapp.delivery.domain.kitchen.Kitchen;
import com.myapp.delivery.domain.menu_item.Category;
import com.myapp.delivery.web.dto.ingredient.IngredientDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class MenuItemDto {

  @Schema(description = "Уникальный идентификатор", example = "1")
  private Long id;

  @Schema(description = "Уникальный идентификатор ресторана", example = "1")
  private Long restaurantId;

  @Schema(description = "Название блюда", example = "Салат Цезарь")
  private String name;

  @Schema(description = "Текстовое описание блюда", example = "Сытный салат с листьями салата айсберг, с помидорками черри и великолепныи соусом Цезарь.")
  private String description;

  @Schema(description = "Вид кухни")
  private Kitchen kitchen;

  @Schema(description = "Цена", example = "490")
  private BigDecimal price;

  @Schema(description = "Категория блюда", allowableValues = {"Закуска", "Основное блюдо", "Гарнир", "Салат", "Напиток", "Суп", "Десерт"})
  private Category category;

  @Schema(description = "Url для картинки", example = "123214123.png", accessMode = Schema.AccessMode.READ_ONLY)
  private String imageUrl;

  @Schema(description = "Флаг для обозначения доступности данного блюда для заказа", example = "true")
  private Boolean availabilityStatus;

  @Schema(description = "Вес блюда в граммах", example = "500")
  private int weight;

  @Schema(description = "Калории блюда", example = "800")
  private int calories;

  @Schema(description = "Ингридиенты блюда")
  private Set<IngredientDto> ingredients;
}

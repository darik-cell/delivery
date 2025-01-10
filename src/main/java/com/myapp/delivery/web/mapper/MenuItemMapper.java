package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.kitchen.Kitchen;
import com.myapp.delivery.domain.menu_item.MenuItem;
import com.myapp.delivery.web.dto.menu_item.MenuItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = IngredientMapper.class)
public interface MenuItemMapper {

  @Mapping(target = "ingredients", source = "ingredients")
  MenuItem toEntity(MenuItemDto menuItemDto);

  @Mapping(target = "ingredients", source = "ingredients")
  MenuItemDto toDto(MenuItem menuItem);

  List<MenuItemDto> toDto(List<MenuItem> menuItemList);
}

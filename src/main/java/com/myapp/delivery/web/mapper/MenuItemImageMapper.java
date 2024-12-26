package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.menu_item.MenuItemImage;
import com.myapp.delivery.web.dto.menu_item.MenuItemImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuItemImageMapper {

  MenuItemImage toEntity(MenuItemImageDto imageDto);

  MenuItemImageDto toDto(MenuItemImage image);
}

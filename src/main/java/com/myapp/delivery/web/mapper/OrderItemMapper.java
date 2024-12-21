package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.order_item.OrderItem;
import com.myapp.delivery.web.dto.order_item.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = MenuItemMapper.class)
public interface OrderItemMapper {

  @Mapping(target = "menuItem", source = "menuItem")
  OrderItemDto toOrderItemDto(OrderItem orderItem);

  @Mapping(target = "menuItem", source = "menuItem")
  OrderItem toOrderItem(OrderItemDto orderItemDto);

  List<OrderItemDto> toOrderItemDto(List<OrderItem> orderItems);
}

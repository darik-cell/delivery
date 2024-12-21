package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.web.dto.order.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = OrderItemMapper.class)
public interface OrderMapper {

  @Mapping(target = "orderItems", source = "orderItems")
  OrderDto toOrderDto(Order order);

  @Mapping(target = "orderItems", source = "orderItems")
  Order toOrder(OrderDto orderItemDto);

  List<OrderDto> toOrderDto(List<Order> orderItems);
}

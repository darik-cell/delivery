package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.order.Order;
import com.myapp.delivery.web.dto.order.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = OrderItemMapper.class)
public interface OrderMapper {

  @Mapping(target = "orderItems", source = "orderItems")
  @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapTimestampToString")
  @Mapping(target = "deliveryTime", source = "deliveryTime", qualifiedByName = "mapTimestampToString")
  OrderDto toOrderDto(Order order);

  @Mapping(target = "orderItems", source = "orderItems")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "deliveryTime", ignore = true)
  Order toOrder(OrderDto orderItemDto);

  List<OrderDto> toOrderDto(List<Order> orderItems);

  @Named("mapTimestampToString")
  default String mapTimestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        // Часовой пояс Самары
        ZoneId samaraZone = ZoneId.of("Europe/Samara");

        // Преобразование в ZonedDateTime
        ZonedDateTime zonedDateTime = timestamp.toInstant()
                                               .atZone(samaraZone);

        // Форматирование
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return zonedDateTime.format(formatter);
    }
}

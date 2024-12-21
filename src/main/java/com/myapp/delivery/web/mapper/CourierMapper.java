package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.courier.Courier;
import com.myapp.delivery.web.dto.courier.CourierDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourierMapper {

  Courier toEntity(CourierDto courierDto);

  CourierDto toDto(Courier courier);

  List<CourierDto> toDto(List<Courier> couriers);
}

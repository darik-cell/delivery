package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.web.dto.user.UserDto;
import com.myapp.delivery.web.dto.user.UserWithOrdersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = OrderMapper.class)
public interface UserMapper {

  User toUser(UserDto userDto);

  UserDto fromUserToUserDto(User user);

  List<UserDto> fromUserToUserDto(List<User> users);

  @Mapping(target = "orders", source = "orders")
  UserWithOrdersDto toUserWithOrdersDto(User user);

}

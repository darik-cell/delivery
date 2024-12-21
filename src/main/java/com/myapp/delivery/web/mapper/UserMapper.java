package com.myapp.delivery.web.mapper;

import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.web.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  User toUser(UserDto userDto);

  UserDto fromUserToUserDto(User user);
}

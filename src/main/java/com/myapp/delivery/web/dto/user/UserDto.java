package com.myapp.delivery.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myapp.delivery.web.dto.validation.OnCreate;
import com.myapp.delivery.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@ToString
public class UserDto {

  private Long id;

  private String name;

  private String username;

  private String password;

  private String passwordConfirmation;
}

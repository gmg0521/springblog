package com.merchandise.eeblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class UserForm {
  private String username;
  private String password;
  private String email;
  private String role;
}
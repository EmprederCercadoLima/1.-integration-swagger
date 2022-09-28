package com.integration.swagger.response;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@AllArgsConstructor 
@NoArgsConstructor  
@Getter 
@Setter 
public class FindUserCommerceResponse {

  private String idUser;
  private String email;
  private String password;
  
}

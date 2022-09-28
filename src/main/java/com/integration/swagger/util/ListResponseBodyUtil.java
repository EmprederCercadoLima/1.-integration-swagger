package com.integration.swagger.util;

import java.util.List;

public class ListResponseBodyUtil<T> {

  private List<T> body;

  public List<T> getBody() {
    return body;
  }

  public void setBody(List<T> body) {
    this.body = body;
  }
  
}

package com.integration.swagger.util;

public class LoadResponseBodyUtil<T> extends ResponseMetadataUtil {

  private ListResponseBodyUtil<T> response = new ListResponseBodyUtil<T>();
  
  public T getResponse() {
    return (T) response;
  }

  public void setResponse(T response) {
    this.response = (ListResponseBodyUtil<T>) response;
  }
  
}

package com.kentkart.api.exception;

import lombok.Getter;

@Getter
public class BadParametersException extends RuntimeException {

  public BadParametersException(String message) {
    super(message);
  }

}

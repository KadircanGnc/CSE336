package com.kentkart.api.mock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MockObjectCreationResponse {

  private String id;

  private String classname;

  public MockObjectCreationResponse(String id, String classname) {
    this.id = id;
    this.classname = classname;
  }
}

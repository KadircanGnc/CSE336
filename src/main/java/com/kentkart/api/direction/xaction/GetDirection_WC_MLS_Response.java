package com.kentkart.api.direction.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.direction.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDirection_WC_MLS_Response {

  private String id;

  private String name;

  public GetDirection_WC_MLS_Response(Direction direction) {
    this.id = direction.getId();
    this.name = direction.getName();
  }
}

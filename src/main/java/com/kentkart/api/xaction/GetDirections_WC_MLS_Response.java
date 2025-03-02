package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.direction.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDirections_WC_MLS_Response {

  private String id;

  private String name;

  public GetDirections_WC_MLS_Response(Direction direction) {
    this.id = direction.getId();
    this.name = direction.getName();
  }
}

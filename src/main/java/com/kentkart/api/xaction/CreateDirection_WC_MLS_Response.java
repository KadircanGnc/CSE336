package com.kentkart.api.xaction;

import com.kentkart.api.direction.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDirection_WC_MLS_Response {
  private String id;

  public CreateDirection_WC_MLS_Response(Direction direction) {
    this.id = direction.getId();
  }
}

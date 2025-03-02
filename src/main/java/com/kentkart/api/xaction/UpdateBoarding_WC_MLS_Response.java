package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.boarding.Boarding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBoarding_WC_MLS_Response {

  private String id;

  public UpdateBoarding_WC_MLS_Response(Boarding boarding) {
    this.id = boarding.getId();
  }
}

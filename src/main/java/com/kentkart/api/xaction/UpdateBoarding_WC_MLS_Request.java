package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBoarding_WC_MLS_Request {
  private String passengerId;

  private String passengerType;

  private Long boardingTime;

  private String busStopId;

  private Double latitude;

  private Double longitude;

  private String tripId;

  private String boardingTypeName;
}

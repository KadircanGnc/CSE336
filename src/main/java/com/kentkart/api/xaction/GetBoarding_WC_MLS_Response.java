package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.boarding.Boarding;
import com.kentkart.api.boardingtype.BoardingType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBoarding_WC_MLS_Response {
  private String id;

  private String passengerId;

  private String passengerType;

  private Long boardingTime;

  private String busStopId;

  private Double latitude;

  private Double longitude;

  private String tripId;

  private BoardingType boardingType;

  public GetBoarding_WC_MLS_Response(Boarding boarding) {
    this.id = boarding.getId();
    this.passengerId = boarding.getPassengerId();
    this.passengerType = boarding.getPassengerType();
    this.boardingTime = boarding.getBoardingTime();
    this.busStopId = boarding.getBusStopId();
    this.latitude = boarding.getLatitude();
    this.longitude = boarding.getLongitude();
    this.tripId = boarding.getTripId();
    this.boardingType = boarding.getBoardingType();
  }
}

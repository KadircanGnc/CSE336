package com.kentkart.api.boarding;

import org.hibernate.annotations.UuidGenerator;

import com.kentkart.api.boardingtype.BoardingType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Boarding {

  @Id
  @UuidGenerator
  private String id;

  private String passengerId;

  private String passengerType;

  private Long boardingTime;

  private String busStopId;

  private Double latitude;

  private Double longitude;

  private String tripId;

  @ManyToOne
  @JoinColumn(name = "boarding_type_id")
  private BoardingType boardingType;
}

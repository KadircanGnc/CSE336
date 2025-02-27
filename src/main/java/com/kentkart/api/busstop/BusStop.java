package com.kentkart.api.busstop;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BusStop {

  @Id
  @UuidGenerator
  private String id;

  private Double latitude;

  private Double longitude;

  private String stopName;
}

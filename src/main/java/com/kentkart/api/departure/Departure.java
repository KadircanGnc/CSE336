package com.kentkart.api.departure;

import org.hibernate.annotations.UuidGenerator;

import com.kentkart.api.route.Route;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Departure {

  @Id
  @UuidGenerator
  private String id;

  @ManyToOne
  @JoinColumn(name = "route_id")
  private Route route;

  @ManyToOne
  @JoinColumn(name = "departure_day_id")
  private DepartureDay departureDay;

}

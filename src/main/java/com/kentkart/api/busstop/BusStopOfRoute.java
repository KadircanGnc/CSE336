package com.kentkart.api.busstop;

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
public class BusStopOfRoute {

  @Id
  @UuidGenerator
  private String id;

  @ManyToOne
  @JoinColumn(name = "route_id")
  private Route route;

  @ManyToOne
  @JoinColumn(name = "bus_stop_id")
  private BusStop busStop;

  private Integer sequence;

  private Long arrivalOffset;

  private Long departureOffset;
}

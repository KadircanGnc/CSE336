package com.kentkart.api.point;

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
public class Point {

  @Id
  @UuidGenerator
  private String id;

  @ManyToOne
  @JoinColumn(name = "route_id")
  private Route route;

  private Integer sequence;

  private Double latitude;

  private Double longitude;
}

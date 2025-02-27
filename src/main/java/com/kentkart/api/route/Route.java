package com.kentkart.api.route;

import org.hibernate.annotations.UuidGenerator;

import com.kentkart.api.direction.Direction;
import com.kentkart.api.line.Line;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Route {

  @Id
  @UuidGenerator
  private String id;

  @ManyToOne
  @JoinColumn(name = "direction_id")
  private Direction direction;

  @ManyToOne
  @JoinColumn(name = "line_id")
  private Line line;
}

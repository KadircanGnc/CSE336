package com.kentkart.api.direction;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Direction {

  @Id
  @UuidGenerator
  private String id;

  private String name;
  
}

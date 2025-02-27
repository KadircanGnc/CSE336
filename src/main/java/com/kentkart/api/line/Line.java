package com.kentkart.api.line;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Line {

  @Id
  @UuidGenerator
  private String id;

  private String lineCode;

  private String lineCodeRepresentation;
}

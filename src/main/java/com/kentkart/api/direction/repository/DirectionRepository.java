package com.kentkart.api.direction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.direction.Direction;

public interface DirectionRepository extends JpaRepository<Direction, String> {

  Optional<Direction> findByName(String name);
}

package com.kentkart.api.direction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.direction.Direction;

public interface DirectionRepository extends JpaRepository<Direction, String> {
    
}

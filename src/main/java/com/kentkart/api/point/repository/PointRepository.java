package com.kentkart.api.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.point.Point;

public interface PointRepository extends JpaRepository<Point, String> {
    
}

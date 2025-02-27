package com.kentkart.api.line.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.line.Line;

public interface LineRepository extends JpaRepository<Line, String> {
    
}

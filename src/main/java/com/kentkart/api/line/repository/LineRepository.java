package com.kentkart.api.line.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.line.Line;

public interface LineRepository extends JpaRepository<Line, String> {
    
    Optional<Line> findByLineCode(String lineCode);
}

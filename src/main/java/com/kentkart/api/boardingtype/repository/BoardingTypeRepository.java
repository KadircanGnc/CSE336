package com.kentkart.api.boardingtype.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.boardingtype.BoardingType;

public interface BoardingTypeRepository extends JpaRepository<BoardingType, String> {
    
}

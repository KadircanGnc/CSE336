package com.kentkart.api.departure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.departure.Departure;

public interface DepartureRepository extends JpaRepository<Departure, String> {
    
}

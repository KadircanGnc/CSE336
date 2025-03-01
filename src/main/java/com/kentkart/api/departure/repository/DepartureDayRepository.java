package com.kentkart.api.departure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.departure.DepartureDay;

public interface DepartureDayRepository extends JpaRepository<DepartureDay, String> {
    
    Optional<DepartureDay> findByDay(String day);
}

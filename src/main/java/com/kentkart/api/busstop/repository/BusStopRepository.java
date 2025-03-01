package com.kentkart.api.busstop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.busstop.BusStop;

public interface BusStopRepository extends JpaRepository<BusStop, String> {
    
    Optional<BusStop> findByStopName(String stopName);
}

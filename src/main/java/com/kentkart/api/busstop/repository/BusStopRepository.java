package com.kentkart.api.busstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.busstop.BusStop;

public interface BusStopRepository extends JpaRepository<BusStop, String> {
    
}

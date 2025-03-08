package com.kentkart.api.appfilters.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.boarding.Boarding;
import com.kentkart.api.boarding.service.BoardingService;
import com.kentkart.api.busstop.BusStop;
import com.kentkart.api.busstop.service.BusStopService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/app-filters")
@RequiredArgsConstructor
public class AppFiltersController {

  private final BoardingService boardingService;

  private final BusStopService busStopService;

  @GetMapping("/boardings/passenger-ids")
  public ResponseEntity<List<String>> getPassengerIds() {
    Page<Boarding> boardings = boardingService.getAll(null, null, null, null, null, Pageable.unpaged());
    List<String> passengerIds = new ArrayList<>();
    for (Boarding boarding : boardings) {
      if (!passengerIds.contains(boarding.getPassengerId())) {
        passengerIds.add(boarding.getPassengerId());
      }
    }
    return ResponseEntity.ok(passengerIds);
  }

  @GetMapping("/boardings/passenger-types")
  public ResponseEntity<List<String>> getPassengerTypes() {
    Page<Boarding> boardings = boardingService.getAll(null, null, null, null, null, Pageable.unpaged());
    List<String> passengerTypes = new ArrayList<>();
    for (Boarding boarding : boardings) {
      if (!passengerTypes.contains(boarding.getPassengerType())) {
        passengerTypes.add(boarding.getPassengerType());
      }
    }
    return ResponseEntity.ok(passengerTypes);
  }

  @GetMapping("/bus-stops")
  public ResponseEntity<List<String>> getBusStops() {
    Page<BusStop> busStops = busStopService.getAll(Pageable.unpaged());
    List<String> busStopIds = new ArrayList<>();
    for (BusStop busStop : busStops) {
      if (!busStopIds.contains(busStop.getId())) {
        busStopIds.add(busStop.getId());
      }
    }
    return ResponseEntity.ok(busStopIds);
  }

}

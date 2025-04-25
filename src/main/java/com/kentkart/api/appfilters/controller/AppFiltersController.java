package com.kentkart.api.appfilters.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.boarding.Boarding;
import com.kentkart.api.boarding.service.BoardingService;
import com.kentkart.api.boardingtype.BoardingType;
import com.kentkart.api.boardingtype.service.BoardingTypeService;
import com.kentkart.api.busstop.BusStop;
import com.kentkart.api.busstop.service.BusStopService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/app-filters")
@RequiredArgsConstructor
public class AppFiltersController {

  private final BoardingService boardingService;

  private final BusStopService busStopService;

  private final BoardingTypeService boardingTypeService;

  @GetMapping("/boardings/passenger-ids")
  public ResponseEntity<Set<String>> getPassengerIds() {
    Page<Boarding> boardings = boardingService.getAll(null, null, null, null, null, Pageable.unpaged());
    Set<String> passengerIds = new HashSet<>();
    for (Boarding boarding : boardings) {
      if (!passengerIds.contains(boarding.getPassengerId())) {
        passengerIds.add(boarding.getPassengerId());
      }
    }
    return ResponseEntity.ok(passengerIds);
  }

  @GetMapping("/boardings/passenger-types")
  public ResponseEntity<Set<String>> getPassengerTypes() {
    Page<Boarding> boardings = boardingService.getAll(null, null, null, null, null, Pageable.unpaged());
    Set<String> passengerTypes = new HashSet<>();
    for (Boarding boarding : boardings) {
      if (!passengerTypes.contains(boarding.getPassengerType())) {
        passengerTypes.add(boarding.getPassengerType());
      }
    }
    return ResponseEntity.ok(passengerTypes);
  }

  @GetMapping("/bus-stops")
  public ResponseEntity<Set<String>> getBusStops() {
    Page<BusStop> busStops = busStopService.getAll(Pageable.unpaged());
    Set<String> busStopIds = new HashSet<>();
    for (BusStop busStop : busStops) {
      if (!busStopIds.contains(busStop.getId())) {
        busStopIds.add(busStop.getId());
      }
    }
    return ResponseEntity.ok(busStopIds);
  }

  @GetMapping("/boardings/trips")
  public ResponseEntity<Set<String>> getTrips() {
    Page<Boarding> boardings = boardingService.getAll(null, null, null, null, null, Pageable.unpaged());
    Set<String> tripIds = new HashSet<>();
    for (Boarding boarding : boardings) {
      if (!tripIds.contains(boarding.getTripId())) {
        tripIds.add(boarding.getTripId());
      }
    }
    return ResponseEntity.ok(tripIds);
  }

  @GetMapping("/boarding-type-ids")
  public ResponseEntity<Set<String>> getBoardingTypeIds() {
    Page<BoardingType> boardingTypes = boardingTypeService.getAll(Pageable.unpaged());
    Set<String> boardingTypeIds = new HashSet<>();
    for (BoardingType boardingType : boardingTypes) {
      if (!boardingTypeIds.contains(boardingType.getId())) {
        boardingTypeIds.add(boardingType.getId());
      }
    }
    return ResponseEntity.ok(boardingTypeIds);
  }
}

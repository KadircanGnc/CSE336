package com.kentkart.api.boarding.specifitacion;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.kentkart.api.boarding.Boarding;

@Component
public class BoardingSpecification {

  private Specification<Boarding> hasPassengerIds(String[] passengerIds) {
    return (root, query, cb) -> root.get("passengerId").in((Object[]) passengerIds);
  }

  private Specification<Boarding> hasPassengerTypes(String[] passengerTypes) {
    return (root, query, cb) -> root.get("passengerType").in((Object[]) passengerTypes);
  }

  private Specification<Boarding> hasBusStopIds(String[] busStopIds) {
    return (root, query, cb) -> root.get("busStopId").in((Object[]) busStopIds);
  }

  private Specification<Boarding> hasTripIds(String[] tripIds) {
    return (root, query, cb) -> root.get("tripId").in((Object[]) tripIds);
  }

  private Specification<Boarding> hasBoardingTypeIds(String[] boardingTypeIds) {
    return (root, query, cb) -> root.get("boardingType").get("id").in((Object[]) boardingTypeIds);
  }

  public Specification<Boarding> conditionalSearch(
      @RequestParam(required = false) String[] passengerIds,
      @RequestParam(required = false) String[] passengerTypes,
      @RequestParam(required = false) String[] busStopIds,
      @RequestParam(required = false) String[] tripIds,
      @RequestParam(required = false) String[] boardingTypeIds) {

    Specification<Boarding> spec = Specification.where(null);
    if (passengerIds != null && passengerIds.length > 0) {
      spec = spec.and(hasPassengerIds(passengerIds));
    }

    if (passengerTypes != null && passengerTypes.length > 0) {
      spec = spec.and(hasPassengerTypes(passengerTypes));
    }

    if (busStopIds != null && busStopIds.length > 0) {
      spec = spec.and(hasBusStopIds(busStopIds));
    }

    if (tripIds != null && tripIds.length > 0) {
      spec = spec.and(hasTripIds(tripIds));
    }

    if (boardingTypeIds != null && boardingTypeIds.length > 0) {
      spec = spec.and(hasBoardingTypeIds(boardingTypeIds));
    }

    return spec;
  }

}

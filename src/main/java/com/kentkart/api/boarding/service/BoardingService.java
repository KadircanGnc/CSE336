package com.kentkart.api.boarding.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.boarding.Boarding;
import com.kentkart.api.boarding.repository.BoardingRepository;
import com.kentkart.api.boarding.specifitacion.BoardingSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardingService {

  private final BoardingRepository boardingRepository;

  private final BoardingSpecification boardingSpecification;

  public Boarding create(Boarding boarding) {
    return boardingRepository.save(boarding);
  }

  public Boarding getById(String id) {
    return boardingRepository.findById(id).orElse(null);
  }

  public Page<Boarding> getAll(
      String[] passengerIds,
      String[] passengerTypes,
      String[] busStopIds,
      String[] tripIds,
      String[] boardingTypeIds,
      Pageable pageable) {

    return boardingRepository.findAll(
        boardingSpecification.conditionalSearch(passengerIds, passengerTypes, busStopIds, tripIds, boardingTypeIds),
        pageable);
  }

  public void delete(Boarding boarding) {
    boardingRepository.delete(boarding);
  }
}

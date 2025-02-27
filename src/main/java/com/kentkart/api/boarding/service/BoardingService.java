package com.kentkart.api.boarding.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.boarding.Boarding;
import com.kentkart.api.boarding.repository.BoardingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardingService {

  private final BoardingRepository boardingRepository;

  public Boarding create(Boarding boarding) {
    return boardingRepository.save(boarding);
  }

  public Boarding getById(String id) {
    return boardingRepository.findById(id).orElse(null);
  }

  public Page<Boarding> getAll(Pageable pageable) {
    return boardingRepository.findAll(pageable);
  }

  public void delete(String id) {
    boardingRepository.deleteById(id);
  }
}

package com.kentkart.api.departure.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.departure.Departure;
import com.kentkart.api.departure.repository.DepartureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartureService {
    
    private final DepartureRepository departureRepository;

    public Departure create(Departure departure) {
        return departureRepository.save(departure);
    }

    public Departure getById(String id) {
        return departureRepository.findById(id).orElse(null);
    }

    public Page<Departure> getAll(Pageable pageable) {
        return departureRepository.findAll(pageable);
    }

    public void delete(Departure departure) {
        departureRepository.delete(departure);
    }
}

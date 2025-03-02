package com.kentkart.api.departure.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.departure.DepartureDay;
import com.kentkart.api.departure.repository.DepartureDayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartureDayService {

    private final DepartureDayRepository departureDayRepository;

    public DepartureDay create(DepartureDay departureDay) {
        return departureDayRepository.save(departureDay);
    }

    public DepartureDay getById(String id) {
        return departureDayRepository.findById(id).orElse(null);
    }

    public DepartureDay getByDay(String day) {
        return departureDayRepository.findByDay(day).orElse(null);
    }

    public Page<DepartureDay> getAll(Pageable pageable) {
        return departureDayRepository.findAll(pageable);
    }

    public void delete(String id) {
        departureDayRepository.deleteById(id);
    }
}

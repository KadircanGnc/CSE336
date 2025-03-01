package com.kentkart.api.busstop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.busstop.BusStop;
import com.kentkart.api.busstop.repository.BusStopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusStopService {
    
    private final BusStopRepository busStopRepository;

    public BusStop create(BusStop busStop) {
        return busStopRepository.save(busStop);
    }

    public BusStop getById(String id) {
        return busStopRepository.findById(id).orElse(null);
    }

    public BusStop getByStopName(String stopName) {
        return busStopRepository.findByStopName(stopName).orElse(null);
    }

    public Page<BusStop> getAll(Pageable pageable) {
        return busStopRepository.findAll(pageable);
    }

    public void delete(String id) {
        busStopRepository.deleteById(id);
    }
}

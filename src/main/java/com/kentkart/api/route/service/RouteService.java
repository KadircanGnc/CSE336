package com.kentkart.api.route.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.route.Route;
import com.kentkart.api.route.repository.RouteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteService {
    
    private final RouteRepository routeRepository;
    
    public Route create(Route route) {
        return routeRepository.save(route);
    }

    public Route getById(String id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Page<Route> getAll(Pageable pageable) {
        return routeRepository.findAll(pageable);
    }

    public void delete(String id) {
        routeRepository.deleteById(id);
    }
}

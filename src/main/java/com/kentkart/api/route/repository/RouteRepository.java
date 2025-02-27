package com.kentkart.api.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kentkart.api.route.Route;

public interface RouteRepository extends JpaRepository<Route, String> {
    
}

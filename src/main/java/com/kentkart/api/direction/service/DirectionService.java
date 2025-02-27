package com.kentkart.api.direction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.direction.Direction;
import com.kentkart.api.direction.repository.DirectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DirectionService {
    
    private final DirectionRepository directionRepository;

    public Direction create(Direction direction) {
        return directionRepository.save(direction);
    }

    public Direction getById(String id) {
        return directionRepository.findById(id).orElse(null);
    }

    public Page<Direction> getAll(Pageable pageable) {
        return directionRepository.findAll(pageable);
    }

    public void delete(String id) {
        directionRepository.deleteById(id);
    }
}

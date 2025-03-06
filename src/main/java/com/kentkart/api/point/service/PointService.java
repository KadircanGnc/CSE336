package com.kentkart.api.point.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.point.Point;
import com.kentkart.api.point.repository.PointRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService {
    
    private final PointRepository pointRepository;

    public Point create(Point point) {
        return pointRepository.save(point);
    }

    public Point getById(String id) {
        return pointRepository.findById(id).orElse(null);
    }

    public Page<Point> getAll(Pageable pageable) {
        return pointRepository.findAll(pageable);
    }

    public void delete(Point point) {
        pointRepository.delete(point);
    }

}

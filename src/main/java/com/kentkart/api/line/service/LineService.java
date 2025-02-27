package com.kentkart.api.line.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.line.Line;
import com.kentkart.api.line.repository.LineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LineService {
    
    private final LineRepository lineRepository;

    public Line create(Line line) {
        return lineRepository.save(line);
    }

    public Line getById(String id) {
        return lineRepository.findById(id).orElse(null);
    }

    public Page<Line> getAll(Pageable pageable) {
        return lineRepository.findAll(pageable);
    }

    public void delete(String id) {
        lineRepository.deleteById(id);
    }

}

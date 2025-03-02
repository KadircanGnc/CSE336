package com.kentkart.api.boardingtype.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kentkart.api.boardingtype.BoardingType;
import com.kentkart.api.boardingtype.repository.BoardingTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardingTypeService {

    private final BoardingTypeRepository boardingTypeRepository;

    public BoardingType create(BoardingType boardingType) {
        return boardingTypeRepository.save(boardingType);
    }

    public BoardingType getById(String id) {
        return boardingTypeRepository.findById(id).orElse(null);
    }

    public BoardingType getByName(String name) {
        return boardingTypeRepository.findByName(name).orElse(null);
    }

    public Page<BoardingType> getAll(Pageable pageable) {
        return boardingTypeRepository.findAll(pageable);
    }

    public void delete(BoardingType boardingType) {
        boardingTypeRepository.delete(boardingType);
    }
}

package com.kentkart.api.exception.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kentkart.api.exception.BadParametersException;
import com.kentkart.api.exception.NotFoundException;

@RestControllerAdvice
public class RestAdvice {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
    return ResponseEntity.status(404).body(e.getMessage());
  }

  @ExceptionHandler(BadParametersException.class)
  public ResponseEntity<String> handleBadParametersException(BadParametersException e) {
    return ResponseEntity.status(400).body(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception e) {
    return ResponseEntity.status(500).body(e.getMessage());
  }
}

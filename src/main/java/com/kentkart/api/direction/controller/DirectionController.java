package com.kentkart.api.direction.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.direction.Direction;
import com.kentkart.api.direction.service.DirectionService;
import com.kentkart.api.direction.xaction.CreateDirection_WC_MLS_Request;
import com.kentkart.api.direction.xaction.CreateDirection_WC_MLS_Response;
import com.kentkart.api.direction.xaction.GetDirection_WC_MLS_Response;
import com.kentkart.api.direction.xaction.GetDirections_WC_MLS_Response;
import com.kentkart.api.direction.xaction.UpdateDirection_WC_MLS_Request;
import com.kentkart.api.direction.xaction.UpdateDirection_WC_MLS_Response;
import com.kentkart.api.exception.BadParametersException;
import com.kentkart.api.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/directions")
@RequiredArgsConstructor
public class DirectionController {

  private final DirectionService directionService;

  @GetMapping
  public ResponseEntity<Page<GetDirections_WC_MLS_Response>> getDirections(Pageable pageable) {
    return ResponseEntity.ok(directionService.getAll(pageable).map(GetDirections_WC_MLS_Response::new));
  }

  @PostMapping
  public ResponseEntity<CreateDirection_WC_MLS_Response> createDirection(
      @RequestBody CreateDirection_WC_MLS_Request request) {
    Direction directionInDB = directionService.getByName(request.getName());
    if (directionInDB != null) {
      throw new BadParametersException("Direction with name " + request.getName() + " already exists");
    }
    directionInDB = new Direction();
    directionInDB.setName(request.getName());
    directionInDB = directionService.create(directionInDB);
    return ResponseEntity.ok(new CreateDirection_WC_MLS_Response(directionInDB));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetDirection_WC_MLS_Response> getDirection(@PathVariable String id) {
    Direction direction = directionService.getById(id);
    if (direction == null) {
      throw new NotFoundException("Direction with id " + id + " not found");
    }
    return ResponseEntity.ok(new GetDirection_WC_MLS_Response(direction));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdateDirection_WC_MLS_Response> updateDirection(@PathVariable String id,
      @RequestBody UpdateDirection_WC_MLS_Request request) {
    Direction direction = directionService.getById(id);
    if (direction == null) {
      throw new NotFoundException("Direction with id " + id + " not found");
    }
    direction.setName(request.getName());
    direction = directionService.create(direction); // why create not update?
    return ResponseEntity.ok(new UpdateDirection_WC_MLS_Response(direction));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDirection(@PathVariable String id) {
    Direction direction = directionService.getById(id);
    if (direction == null) {
      throw new NotFoundException("Direction with id " + id + " not found");
    }
    directionService.delete(id);
    return ResponseEntity.ok().build();
  }
}

package com.kentkart.api.boarding.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.boarding.Boarding;
import com.kentkart.api.boarding.service.BoardingService;
import com.kentkart.api.boardingtype.BoardingType;
import com.kentkart.api.boardingtype.service.BoardingTypeService;
import com.kentkart.api.xaction.CreateBoarding_WC_MLS_Request;
import com.kentkart.api.xaction.CreateBoarding_WC_MLS_Response;
import com.kentkart.api.xaction.GetBoardings_WC_MLS_Response;
import com.kentkart.api.xaction.UpdateBoarding_WC_MLS_Request;
import com.kentkart.api.xaction.UpdateBoarding_WC_MLS_Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/boardings")
@RequiredArgsConstructor
public class BoardingController {

  private final BoardingService boardingService;

  private final BoardingTypeService boardingTypeService;

  @GetMapping
  public ResponseEntity<Page<GetBoardings_WC_MLS_Response>> getBoardings(
      @RequestParam(required = false) String[] passengerIds,
      @RequestParam(required = false) String[] passengerTypes,
      @RequestParam(required = false) String[] busStopIds,
      @RequestParam(required = false) String[] tripIds,
      @RequestParam(required = false) String[] boardingTypeIds,
      Pageable pageable) {
    Page<GetBoardings_WC_MLS_Response> boardings = boardingService.getAll(passengerIds,
        passengerTypes,
        busStopIds,
        tripIds,
        boardingTypeIds,
        pageable)
        .map(GetBoardings_WC_MLS_Response::new);
    return ResponseEntity.ok(boardings);
  }

  @PostMapping
  public ResponseEntity<CreateBoarding_WC_MLS_Response> createBoarding(
      @RequestBody CreateBoarding_WC_MLS_Request request) {
    BoardingType boardingTypeInDB = boardingTypeService.getByName(request.getBoardingTypeName());
    if (boardingTypeInDB == null) {
      throw new IllegalArgumentException("Boarding type not found");
    }
    Boarding boarding = new Boarding();
    boarding.setBoardingType(boardingTypeInDB);
    boarding.setBoardingTime(request.getBoardingTime());
    boarding.setBusStopId(request.getBusStopId());
    boarding.setLatitude(request.getLatitude());
    boarding.setLongitude(request.getLongitude());
    boarding.setPassengerId(request.getPassengerId());
    boarding.setPassengerType(request.getPassengerType());
    boarding.setTripId(request.getTripId());
    boarding = boardingService.create(boarding);
    CreateBoarding_WC_MLS_Response response = new CreateBoarding_WC_MLS_Response(boarding);
    return ResponseEntity.ok(response);
  }

  // TODO: Implement GetById method;

  @PutMapping("/{id}")
  public ResponseEntity<UpdateBoarding_WC_MLS_Response> updateBoarding(@PathVariable String id,
      @RequestBody UpdateBoarding_WC_MLS_Request request) {
    Boarding boardingInDB = boardingService.getById(id);
    if (boardingInDB == null) {
      throw new IllegalArgumentException("Boarding not found");
    }
    BoardingType boardingTypeInDB = boardingTypeService.getByName(request.getBoardingTypeName());
    if (boardingTypeInDB == null) {
      throw new IllegalArgumentException("Boarding type not found");
    }
    boardingInDB.setBoardingType(boardingTypeInDB);
    boardingInDB.setBoardingTime(request.getBoardingTime());
    boardingInDB.setBusStopId(request.getBusStopId());
    boardingInDB.setLatitude(request.getLatitude());
    boardingInDB.setLongitude(request.getLongitude());
    boardingInDB.setPassengerId(request.getPassengerId());
    boardingInDB.setPassengerType(request.getPassengerType());
    boardingInDB.setTripId(request.getTripId());
    boardingInDB = boardingService.create(boardingInDB);

    UpdateBoarding_WC_MLS_Response response = new UpdateBoarding_WC_MLS_Response(boardingInDB);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBoarding(@PathVariable String id) {
    Boarding boardingInDB = boardingService.getById(id);
    if (boardingInDB == null) {
      throw new IllegalArgumentException("Boarding not found");
    }
    boardingService.delete(boardingInDB);
    return ResponseEntity.ok().build();
  }
}

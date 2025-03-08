package com.kentkart.api.mock.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.mock.MockObjectCreationResponse;
import com.kentkart.api.mock.service.MockConstantsService;
import com.kentkart.api.xaction.CreateMockConstants_MLS_Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mock-data")
@RequiredArgsConstructor
public class MockConstantsController {

  private final MockConstantsService mockConstantsService;

  @PostMapping
  public ResponseEntity<CreateMockConstants_MLS_Response> createBoardingTypes(
      @RequestParam Integer count,
      @RequestParam String token) {

    CreateMockConstants_MLS_Response response = new CreateMockConstants_MLS_Response();

    // Create boarding types
    List<MockObjectCreationResponse> responses = mockConstantsService.createBoardingTypes(count);
    Set<String> boardingTypeIds = responses.stream().map(MockObjectCreationResponse::getId).collect(Collectors.toSet());
    response.setBoardingtypeids(boardingTypeIds);

    // Create bus stops
    List<MockObjectCreationResponse> busStopResponses = mockConstantsService.createBusStops(count);
    Set<String> busStopIds = busStopResponses.stream().map(MockObjectCreationResponse::getId)
        .collect(Collectors.toSet());
    response.setBusstopids(busStopIds);

    // Create Directions
    List<MockObjectCreationResponse> directionResponses = mockConstantsService.createDirections(count);
    Set<String> directionIds = directionResponses.stream().map(MockObjectCreationResponse::getId)
        .collect(Collectors.toSet());
    response.setDirectionids(directionIds);

    // Create Lines
    List<MockObjectCreationResponse> lineResponses = mockConstantsService.createLines(count);
    Set<String> lineIds = lineResponses.stream().map(MockObjectCreationResponse::getId).collect(Collectors.toSet());
    response.setLineids(lineIds);

    // Create Routes
    List<MockObjectCreationResponse> routeResponses = mockConstantsService.createRoutes(count);
    Set<String> routeIds = routeResponses.stream().map(MockObjectCreationResponse::getId).collect(Collectors.toSet());
    response.setRouteids(routeIds);

    // Create Points
    List<MockObjectCreationResponse> pointResponses = mockConstantsService.createPoints(count);
    Set<String> pointIds = pointResponses.stream().map(MockObjectCreationResponse::getId).collect(Collectors.toSet());
    response.setPointids(pointIds);

    // Create DepartureDays
    List<MockObjectCreationResponse> departureDayResponses = mockConstantsService.createDepartureDays(count);
    Set<String> departureDayIds = departureDayResponses.stream().map(MockObjectCreationResponse::getId)
        .collect(Collectors.toSet());
    response.setDeparturedayids(departureDayIds);

    // Create Boardings
    List<MockObjectCreationResponse> boardingResponses = mockConstantsService.createBoardings(count);
    Set<String> boardingIds = boardingResponses.stream().map(MockObjectCreationResponse::getId)
        .collect(Collectors.toSet());
    response.setBoardingids(boardingIds);

    // Create Departures
    List<MockObjectCreationResponse> departureResponses = mockConstantsService.createDepartures(count);
    Set<String> departureIds = departureResponses.stream().map(MockObjectCreationResponse::getId)
        .collect(Collectors.toSet());
    response.setDepartureids(departureIds);



    return ResponseEntity.ok(response);
  }
}

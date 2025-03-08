package com.kentkart.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kentkart.api.boarding.Boarding;
import com.kentkart.api.boarding.repository.BoardingRepository;
import com.kentkart.api.boardingtype.BoardingType;
import com.kentkart.api.boardingtype.repository.BoardingTypeRepository;
import com.kentkart.api.busstop.BusStop;
import com.kentkart.api.busstop.repository.BusStopRepository;
import com.kentkart.api.departure.Departure;
import com.kentkart.api.departure.DepartureDay;
import com.kentkart.api.departure.repository.DepartureDayRepository;
import com.kentkart.api.departure.repository.DepartureRepository;
import com.kentkart.api.direction.Direction;
import com.kentkart.api.direction.repository.DirectionRepository;
import com.kentkart.api.line.Line;
import com.kentkart.api.line.repository.LineRepository;
import com.kentkart.api.mock.MockObjectCreationResponse;
import com.kentkart.api.point.Point;
import com.kentkart.api.point.repository.PointRepository;
import com.kentkart.api.route.Route;
import com.kentkart.api.route.repository.RouteRepository;
import com.kentkart.api.utils.RandomizerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MockConstantsService {

  private final BoardingTypeRepository boardingTypeRepository;

  private final BusStopRepository busStopRepository;

  private final DirectionRepository directionRepository;

  private final LineRepository lineRepository;

  private final PointRepository pointRepository;

  private final RouteRepository routeRepository;

  private final DepartureDayRepository departureDayRepository;

  private final BoardingRepository boardingRepository;

  private final DepartureRepository departureRepository;

  private final List<String> passengerIds = RandomizerService.getRandomElements(4);

  private final List<String> tripIds = RandomizerService.getRandomElements(4);

  private final List<String> passengerTypes = RandomizerService.getRandomElements(4);

  public List<MockObjectCreationResponse> createBoardingTypes(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      BoardingType boardingType = new BoardingType();
      boardingType.setName(RandomizerService.createRandomString(10));
      boardingType = boardingTypeRepository.save(boardingType);
      responses.add(new MockObjectCreationResponse(boardingType.getId(), boardingType.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createBusStops(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      BusStop busStop = new BusStop();
      busStop.setStopName(RandomizerService.createRandomString(10));
      busStop.setLatitude(RandomizerService.getRandomLatLng());
      busStop.setLongitude(RandomizerService.getRandomLatLng());
      busStop = busStopRepository.save(busStop);
      responses.add(new MockObjectCreationResponse(busStop.getId(), busStop.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createDirections(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Direction direction = new Direction();
      direction.setName(RandomizerService.createRandomString(10));
      direction = directionRepository.save(direction);
      responses.add(new MockObjectCreationResponse(direction.getId(), direction.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createLines(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Line line = new Line();
      line.setLineCode(RandomizerService.createRandomString(10));
      line.setLineCodeRepresentation(RandomizerService.createRandomString(4));
      line = lineRepository.save(line);
      responses.add(new MockObjectCreationResponse(line.getId(), line.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createPoints(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Point point = new Point();
      point.setRoute(getRandomRoute());
      point.setLatitude(RandomizerService.getRandomLatLng());
      point.setLongitude(RandomizerService.getRandomLatLng());
      point.setSequence(RandomizerService.getRandomNumberInRange(0, 100));
      point = pointRepository.save(point);
      responses.add(new MockObjectCreationResponse(point.getId(), point.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createRoutes(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Route route = new Route();
      route.setLine(getRandomLine());
      route.setDirection(getRandomDirection());
      route = routeRepository.save(route);
      responses.add(new MockObjectCreationResponse(route.getId(), route.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createDepartureDays(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      DepartureDay departureDay = new DepartureDay();
      departureDay.setDay(RandomizerService.createRandomString(5));
      departureDay = departureDayRepository.save(departureDay);
      responses.add(new MockObjectCreationResponse(departureDay.getId(), departureDay.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createBoardings(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    int multipliedCount = count * 1000;
    for (int i = 0; i < multipliedCount; i++) {
      Boarding boarding = new Boarding();
      boarding.setBoardingType(getRandomBoardingType());
      boarding.setBoardingTime(RandomizerService.createRandomTime());
      boarding.setBusStopId(getRandomBusStop().getId());
      boarding.setLatitude(RandomizerService.getRandomLatLng());
      boarding.setLongitude(RandomizerService.getRandomLatLng());
      boarding.setPassengerType(getRandomPassengerType());
      boarding.setTripId(getRandomTripId());
      boarding.setPassengerId(getRandomPassengerId());
      boarding = boardingRepository.save(boarding);

      responses.add(new MockObjectCreationResponse(boarding.getId(), boarding.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createDepartures(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Departure departure = new Departure();
      departure.setRoute(getRandomRoute());
      departure.setDepartureDay(getRandomDepartureDay());
      departure = departureRepository.save(departure);
      responses.add(new MockObjectCreationResponse(departure.getId(), departure.getClass().getSimpleName()));

    }
    return responses;
  }

  private DepartureDay getRandomDepartureDay() {
    List<DepartureDay> departureDays = departureDayRepository.findAll();
    return departureDays.get(RandomizerService.getRandomNumberInRange(0, departureDays.size() - 1));
  }

  private BoardingType getRandomBoardingType() {
    List<BoardingType> boardingTypes = boardingTypeRepository.findAll();
    return boardingTypes.get(RandomizerService.getRandomNumberInRange(0, boardingTypes.size() - 1));
  }

  private BusStop getRandomBusStop() {
    List<BusStop> busStops = busStopRepository.findAll();
    return busStops.get(RandomizerService.getRandomNumberInRange(0, busStops.size() - 1));
  }

  private Line getRandomLine() {
    List<Line> lines = lineRepository.findAll();
    return lines.get(RandomizerService.getRandomNumberInRange(0, lines.size() - 1));
  }

  private Direction getRandomDirection() {
    List<Direction> directions = directionRepository.findAll();
    return directions.get(RandomizerService.getRandomNumberInRange(0, directions.size() - 1));
  }

  private Route getRandomRoute() {
    List<Route> routes = routeRepository.findAll();
    return routes.get(RandomizerService.getRandomNumberInRange(0, routes.size() - 1));
  }

  private String getRandomPassengerId() {
    return passengerIds.get(RandomizerService.getRandomNumberInRange(0, passengerIds.size() - 1));
  }

  private String getRandomTripId() {
    return tripIds.get(RandomizerService.getRandomNumberInRange(0, tripIds.size() - 1));
  }

  private String getRandomPassengerType() {
    return passengerTypes.get(RandomizerService.getRandomNumberInRange(0, passengerTypes.size() - 1));
  }

}

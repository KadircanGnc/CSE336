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

  // private final List<String> passengerIds =
  // RandomizerService.getRandomElements(4);

  // private final List<String> tripIds = RandomizerService.getRandomElements(4);

  // private final List<String> passengerTypes =
  // RandomizerService.getRandomElements(4);

  private static final List<String> BOARDING_TYPES = List.of("Standard", "VIP", "Business Class");

  private static final List<String> BUS_STOP_NAMES = List.of("Downtown", "Central Station", "Park Avenue", "University",
      "Main Street");

  private static final List<String> DIRECTIONS = List.of("North", "South", "East", "West");

  private static final List<String> LINE_CODES = List.of("L1", "L2", "L3", "L4");

  private static final List<String> PASSENGER_TYPES = List.of("Adult", "Senior", "Student", "Child");

  private static final List<String> TRIP_IDS = List.of("T1001", "T1002", "T1003", "T1004");

  private static final List<String> DAYS_OF_THE_WEEK = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
      "Saturday", "Sunday");

  public List<MockObjectCreationResponse> createBoardingTypes(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      BoardingType boardingType = new BoardingType();
      boardingType.setName(BOARDING_TYPES.get(RandomizerService.getRandomNumberInRange(0, BOARDING_TYPES.size() - 1)));
      boardingType = boardingTypeRepository.save(boardingType);
      responses.add(new MockObjectCreationResponse(boardingType.getId(), boardingType.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createBusStops(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      BusStop busStop = new BusStop();
      busStop.setStopName(BUS_STOP_NAMES.get(RandomizerService.getRandomNumberInRange(0, BUS_STOP_NAMES.size() - 1)));
      busStop.setLatitude(getRandomLatitude());
      busStop.setLongitude(getRandomLongitude());
      busStop = busStopRepository.save(busStop);
      responses.add(new MockObjectCreationResponse(busStop.getId(), busStop.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createDirections(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Direction direction = new Direction();
      direction.setName(DIRECTIONS.get(RandomizerService.getRandomNumberInRange(0, DIRECTIONS.size() - 1)));
      direction = directionRepository.save(direction);
      responses.add(new MockObjectCreationResponse(direction.getId(), direction.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createLines(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Line line = new Line();
      line.setLineCode(LINE_CODES.get(RandomizerService.getRandomNumberInRange(0, LINE_CODES.size() - 1)));
      line.setLineCodeRepresentation("Line " + RandomizerService.getRandomNumberInRange(1, 100));
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
      point.setLatitude(getRandomLatitude());
      point.setLongitude(getRandomLongitude());
      point.setSequence(i);
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
      departureDay
          .setDay(DAYS_OF_THE_WEEK.get(RandomizerService.getRandomNumberInRange(0, DAYS_OF_THE_WEEK.size() - 1)));
      departureDay = departureDayRepository.save(departureDay);
      responses.add(new MockObjectCreationResponse(departureDay.getId(), departureDay.getClass().getSimpleName()));
    }
    return responses;
  }

  public List<MockObjectCreationResponse> createBoardings(Integer count) {
    List<MockObjectCreationResponse> responses = new ArrayList<>();
    int multipliedCount = count * 10;
    for (int i = 0; i < multipliedCount; i++) {
      Boarding boarding = new Boarding();
      boarding.setBoardingType(getRandomBoardingType());
      boarding.setBoardingTime(RandomizerService.createRandomTime());
      boarding.setBusStopId(getRandomBusStop().getId());
      boarding.setLatitude(getRandomLatitude());
      boarding.setLongitude(getRandomLongitude());
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
    String passengerType = getRandomPassengerType(); // Assume this method returns values like "Adult", "Senior", etc.
    int passengerNumber = RandomizerService.getRandomNumberInRange(1, 1000);
    return passengerType.toUpperCase() + "-" + String.format("%03d", passengerNumber); // Example: ADULT-001,
                                                                                       // SENIOR-002, etc.
  }

  private String getRandomTripId() {
    return TRIP_IDS.get(RandomizerService.getRandomNumberInRange(0, TRIP_IDS.size() - 1));
  }

  private String getRandomPassengerType() {
    return PASSENGER_TYPES.get(RandomizerService.getRandomNumberInRange(0, PASSENGER_TYPES.size() - 1));
  }

  public double getRandomLatitude() {
    double centerLatitude = 36.884804;
    double delta = 0.01; // about ~1 km variation
    return RandomizerService.getRandomDoubleInRange(centerLatitude - delta, centerLatitude + delta);
}

public double getRandomLongitude() {
    double centerLongitude = 30.704044;
    double delta = 0.01; // about ~1 km variation
    return RandomizerService.getRandomDoubleInRange(centerLongitude - delta, centerLongitude + delta);
}

  

}

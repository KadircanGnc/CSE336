package com.kentkart.api.departure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.departure.Departure;
import com.kentkart.api.departure.DepartureDay;
import com.kentkart.api.departure.service.DepartureDayService;
import com.kentkart.api.departure.service.DepartureService;
import com.kentkart.api.exception.NotFoundException;
import com.kentkart.api.route.Route;
import com.kentkart.api.route.service.RouteService;
import com.kentkart.api.xaction.CreateDepartures_WC_MLS_Request;
import com.kentkart.api.xaction.CreateDepartures_WC_MLS_Response;
import com.kentkart.api.xaction.GetDepartures_WC_MLS_Response;
import com.kentkart.api.xaction.UpdateDepartures_WC_MLS_Request;
import com.kentkart.api.xaction.UpdateDepartures_WC_MLS_Response;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/departures")
@RequiredArgsConstructor
public class DeparturesController {
    
    private final DepartureService departureService;
    private final DepartureDayService departureDayService;
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<Page<GetDepartures_WC_MLS_Response>> getDepartures(
        @RequestParam(required = false) String[] routeIds,
        @RequestParam(required = false) String[] departureDayIds,
        Pageable pageable) {
        Page<GetDepartures_WC_MLS_Response> departures = departureService.getAll(pageable)
            .map(GetDepartures_WC_MLS_Response::new);
        return ResponseEntity.ok(departures);
    }

    @PostMapping
    public ResponseEntity<CreateDepartures_WC_MLS_Response> createDeparture(
        @RequestBody CreateDepartures_WC_MLS_Request request) {

        DepartureDay departureDayInDb = departureDayService.getById(request.getDepartureDayId());
        if (departureDayInDb == null) {
            throw new NotFoundException("Departure day not found");
        }
        Route routeInDb = routeService.getById(request.getRouteId());
        if (routeInDb == null) {
            throw new NotFoundException("Route not found");
        }

        Departure departure = new Departure();
        departure.setRoute(routeInDb);
        departure.setDepartureDay(departureDayInDb);
        departure = departureService.create(departure);

        CreateDepartures_WC_MLS_Response response = new CreateDepartures_WC_MLS_Response(departure);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDepartures_WC_MLS_Response> getDeparture(@PathVariable String id) {
        Departure departure = departureService.getById(id);
        if (departure == null) {
            throw new NotFoundException("Departure not found");
        }
        GetDepartures_WC_MLS_Response response = new GetDepartures_WC_MLS_Response(departure);
        return ResponseEntity.ok(response);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<UpdateDepartures_WC_MLS_Response> updateDeparture(
        @PathVariable String id,
        @RequestBody UpdateDepartures_WC_MLS_Request request) {
        Departure departureInDb = departureService.getById(id);
        if (departureInDb == null) {
            throw new NotFoundException("Departure not found");
        }
        DepartureDay departureDayInDb = departureDayService.getById(request.getDepartureDayId());
        if (departureDayInDb == null) {
            throw new NotFoundException("Departure day not found");
        }
        Route routeInDb = routeService.getById(request.getRouteId());
        if (routeInDb == null) {
            throw new NotFoundException("Route not found");
        }
        departureInDb.setRoute(routeInDb);
        departureInDb.setDepartureDay(departureDayInDb);
        departureInDb = departureService.create(departureInDb);
        UpdateDepartures_WC_MLS_Response response = new UpdateDepartures_WC_MLS_Response(departureInDb);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeparture(@PathVariable String id) {
        Departure departureInDb = departureService.getById(id);
        if (departureInDb == null) {
            throw new NotFoundException("Departure not found");
        }
        departureService.delete(departureInDb);
        return ResponseEntity.ok().build();
    }
    
}

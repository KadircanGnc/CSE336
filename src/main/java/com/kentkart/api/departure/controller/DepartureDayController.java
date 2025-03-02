package com.kentkart.api.departure.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.departure.DepartureDay;
import com.kentkart.api.departure.service.DepartureDayService;
import com.kentkart.api.departure.xaction.departureDay.CreateDepartureDay_WC_MLS_Request;
import com.kentkart.api.departure.xaction.departureDay.CreateDepartureDay_WC_MLS_Response;
import com.kentkart.api.departure.xaction.departureDay.GetDepartureDays_WC_MLS_Response;
import com.kentkart.api.departure.xaction.departureDay.UpdateDepartureDay_WC_MLS_Request;
import com.kentkart.api.departure.xaction.departureDay.UpdateDepartureDay_WC_MLS_Response;
import com.kentkart.api.exception.BadParametersException;
import com.kentkart.api.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/departure-days")
@RequiredArgsConstructor
public class DepartureDayController {

    private final DepartureDayService departureDayService;

    @GetMapping
    public ResponseEntity<Page<GetDepartureDays_WC_MLS_Response>> getDepartureDays(Pageable pageable) {
        return ResponseEntity.ok(departureDayService.getAll(pageable).map(GetDepartureDays_WC_MLS_Response::new));
    }
    
    @PostMapping
    public ResponseEntity<CreateDepartureDay_WC_MLS_Response> createDepartureDay(@RequestBody CreateDepartureDay_WC_MLS_Request request) {
        DepartureDay departureDayInDB = departureDayService.getByDay(request.getDay());
        if (departureDayInDB != null) {
            throw new BadParametersException("DepartureDay with day " + request.getDay() + " already exists");
        }
        departureDayInDB = new DepartureDay();
        departureDayInDB.setDay(request.getDay());
        departureDayInDB = departureDayService.create(departureDayInDB);
        return ResponseEntity.ok(new CreateDepartureDay_WC_MLS_Response(departureDayInDB));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetDepartureDays_WC_MLS_Response> getDepartureDay(@PathVariable String id) {
        DepartureDay departureDay = departureDayService.getById(id);
        if (departureDay == null) {
            throw new NotFoundException("DepartureDay with id " + id + " not found");
        }
        return ResponseEntity.ok(new GetDepartureDays_WC_MLS_Response(departureDay));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateDepartureDay_WC_MLS_Response> updateDepartureDay(@PathVariable String id, @RequestBody UpdateDepartureDay_WC_MLS_Request request) {
        DepartureDay departureDay = departureDayService.getById(id);
        if (departureDay == null) {
            throw new NotFoundException("DepartureDay with id " + id + " not found");
        }
        departureDay.setDay(request.getDay());
        departureDay = departureDayService.create(departureDay);
        return ResponseEntity.ok(new UpdateDepartureDay_WC_MLS_Response(departureDay));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartureDay(@PathVariable String id) {
        DepartureDay departureDay = departureDayService.getById(id);
        if (departureDay == null) {
            throw new NotFoundException("DepartureDay with id " + id + " not found");
        }
        departureDayService.delete(id);
        return ResponseEntity.ok().build();
    }

}

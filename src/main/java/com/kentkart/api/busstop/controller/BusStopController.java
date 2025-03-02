package com.kentkart.api.busstop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.busstop.BusStop;
import com.kentkart.api.busstop.service.BusStopService;
import com.kentkart.api.exception.BadParametersException;
import com.kentkart.api.exception.NotFoundException;
import com.kentkart.api.xaction.CreateBusStop_WC_MLS_Request;
import com.kentkart.api.xaction.CreateBusStop_WC_MLS_Response;
import com.kentkart.api.xaction.GetBusStops_WC_MLS_Response;
import com.kentkart.api.xaction.UpdateBusStop_WC_MLS_Request;
import com.kentkart.api.xaction.UpdateBusStop_WC_MLS_Response;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/bus-stops")
@RequiredArgsConstructor
public class BusStopController {
    
    private final BusStopService busStopService;

    @GetMapping
    public ResponseEntity<Page<GetBusStops_WC_MLS_Response>> getBusStops(Pageable pageable) {
        return ResponseEntity.ok(busStopService.getAll(pageable).map(GetBusStops_WC_MLS_Response::new));
    }

    @PostMapping
    public ResponseEntity<CreateBusStop_WC_MLS_Response> createBusStop(@RequestBody CreateBusStop_WC_MLS_Request request) {
        BusStop busStopInDB = busStopService.getByStopName(request.getStopName());
        if (busStopInDB != null) {
            throw new BadParametersException("BusStop with busStopName " + request.getStopName() + " already exists");
        }
        busStopInDB = new BusStop();
        busStopInDB.setStopName(request.getStopName());
        busStopInDB.setLatitude(request.getLatitude());
        busStopInDB.setLongitude(request.getLongitude());
        busStopInDB = busStopService.create(busStopInDB);
        return ResponseEntity.ok(new CreateBusStop_WC_MLS_Response(busStopInDB));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetBusStops_WC_MLS_Response> getBusStop(@PathVariable String id) {
        BusStop busStop = busStopService.getById(id);
        if (busStop == null) {
            throw new NotFoundException("BusStop with id " + id + " not found");
        }
        return ResponseEntity.ok(new GetBusStops_WC_MLS_Response(busStop));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UpdateBusStop_WC_MLS_Response> updateBusStop(@PathVariable String id, @RequestBody UpdateBusStop_WC_MLS_Request request) {
        BusStop busStop = busStopService.getById(id);
        if (busStop == null) {
            throw new NotFoundException("BusStop with id " + id + " not found");
        }
        busStop.setStopName(request.getStopName());
        busStop.setLatitude(request.getLatitude());
        busStop.setLongitude(request.getLongitude());
        busStop = busStopService.create(busStop);
        return ResponseEntity.ok(new UpdateBusStop_WC_MLS_Response(busStop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusStop(@PathVariable String id) {
        BusStop busStop = busStopService.getById(id);
        if (busStop == null) {
            throw new NotFoundException("BusStop with id " + id + " not found");
        }
        busStopService.delete(id);
        return ResponseEntity.ok().build();
    }

}

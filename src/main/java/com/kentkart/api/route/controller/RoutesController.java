package com.kentkart.api.route.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.direction.Direction;
import com.kentkart.api.direction.service.DirectionService;
import com.kentkart.api.exception.NotFoundException;
import com.kentkart.api.line.Line;
import com.kentkart.api.line.service.LineService;
import com.kentkart.api.route.Route;
import com.kentkart.api.route.service.RouteService;
import com.kentkart.api.xaction.CreateRoute_WC_MLS_Request;
import com.kentkart.api.xaction.CreateRoute_WC_MLS_Response;
import com.kentkart.api.xaction.GetRoutes_WC_MLS_Response;
import com.kentkart.api.xaction.UpdateRoute_WC_MLS_Request;
import com.kentkart.api.xaction.UpdateRoute_WC_MLS_Response;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
public class RoutesController {
    
    private final RouteService routeService;
    private final DirectionService directionService;
    private final LineService lineService;

    @GetMapping
    public ResponseEntity<Page<GetRoutes_WC_MLS_Response>> getRoutes(
        @RequestParam(required = false) String[] directionIds,
        @RequestParam(required = false) String[] lineIds,
        Pageable pageable) {
        Page<GetRoutes_WC_MLS_Response> routes = routeService.getAll(pageable)
            .map(GetRoutes_WC_MLS_Response::new);
        return ResponseEntity.ok(routes);
    }
    
    @PostMapping
    public ResponseEntity<CreateRoute_WC_MLS_Response> createRoute(
        @RequestBody CreateRoute_WC_MLS_Request request) {
        Direction directionInDB = directionService.getById(request.getDirectionId());
        Line lineInDB = lineService.getById(request.getLineId());
        if (directionInDB == null || lineInDB == null) {
            throw new NotFoundException("Direction or Line not found");
        }
        if (request.getDirectionId() == null || request.getLineId() == null) {
            throw new IllegalArgumentException("Direction or Line is required");
        }
        Route route = new Route();
        route.setDirection(directionInDB);
        route.setLine(lineInDB);
        routeService.create(route);
        CreateRoute_WC_MLS_Response response = new CreateRoute_WC_MLS_Response(route);  
        return ResponseEntity.ok(response); 
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetRoutes_WC_MLS_Response> getRouteById(@PathVariable String id) {
        Route route = routeService.getById(id);
        if (route == null) {
            throw new NotFoundException("Route not found");
        }
        GetRoutes_WC_MLS_Response response = new GetRoutes_WC_MLS_Response(route);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateRoute_WC_MLS_Response> updateRoute(
        @PathVariable String id,
        @RequestBody UpdateRoute_WC_MLS_Request request) {
        Route routeInDb = routeService.getById(id);
        if (routeInDb == null) {
            throw new NotFoundException("Route not found");
        }
        Direction directionInDB = directionService.getById(request.getDirectionId());
        Line lineInDB = lineService.getById(request.getLineId());
        if (directionInDB == null || lineInDB == null) {
            throw new NotFoundException("Direction or Line not found");
        }
        if (request.getDirectionId() == null || request.getLineId() == null) {
            throw new IllegalArgumentException("Direction or Line is required");
        }
        routeInDb.setDirection(directionInDB);
        routeInDb.setLine(lineInDB);
        routeService.create(routeInDb);
        UpdateRoute_WC_MLS_Response response = new UpdateRoute_WC_MLS_Response(routeInDb);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String id) {
        Route routeInDb = routeService.getById(id);
        if (routeInDb == null) {
            throw new NotFoundException("Route not found");
        }
        routeService.delete(routeInDb);
        return ResponseEntity.ok().build();
    }
    
}

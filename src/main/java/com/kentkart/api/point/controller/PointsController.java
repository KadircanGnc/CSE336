package com.kentkart.api.point.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.exception.NotFoundException;
import com.kentkart.api.point.Point;
import com.kentkart.api.point.service.PointService;
import com.kentkart.api.route.Route;
import com.kentkart.api.route.service.RouteService;
import com.kentkart.api.xaction.CreatePoints_WC_MLS_Request;
import com.kentkart.api.xaction.CreatePoints_WC_MLS_Response;
import com.kentkart.api.xaction.GetPoints_WC_MLS_Response;
import com.kentkart.api.xaction.UpdatePoints_WC_MLS_Request;
import com.kentkart.api.xaction.UpdatePoints_WC_MLS_Response;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PointsController {
    
    private final PointService pointService;
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<Page<GetPoints_WC_MLS_Response>> getPoints(
        @RequestParam(required = false) String[] routeIds,
        Pageable pageable) {
        Page<GetPoints_WC_MLS_Response> points = pointService.getAll(pageable)
            .map(GetPoints_WC_MLS_Response::new);
        return ResponseEntity.ok(points);
    }
    
    @PostMapping
    public ResponseEntity<CreatePoints_WC_MLS_Response> createPoint(
        @RequestBody CreatePoints_WC_MLS_Request request) {
        Route routeInDB = routeService.getById(request.getRouteId());
        if (routeInDB == null) {
            throw new NotFoundException("Route not found");
        }
        Point point = new Point();
        point.setRoute(routeInDB);
        point.setLatitude(request.getLatitude());
        point.setLongitude(request.getLongitude());
        point.setSequence(request.getSequence());
        pointService.create(point);
        CreatePoints_WC_MLS_Response response = new CreatePoints_WC_MLS_Response(point);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPoints_WC_MLS_Response> getPointById(@PathVariable String id) {
        Point point = pointService.getById(id);
        if (point == null) {
            throw new NotFoundException("Point not found");
        }
        GetPoints_WC_MLS_Response response = new GetPoints_WC_MLS_Response(point);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatePoints_WC_MLS_Response> updatePoint(@PathVariable String id, @RequestBody UpdatePoints_WC_MLS_Request request) {
        Point pointInDB = pointService.getById(id);
        if (pointInDB == null) {
            throw new NotFoundException("Point not found");
        }
        Route routeInDB = routeService.getById(request.getRouteId());
        if (routeInDB == null) {
            throw new NotFoundException("Route not found");
        }
        pointInDB.setRoute(routeInDB);
        pointInDB.setLatitude(request.getLatitude());
        pointInDB.setLongitude(request.getLongitude());
        pointInDB.setSequence(request.getSequence());
        pointService.create(pointInDB);
        UpdatePoints_WC_MLS_Response response = new UpdatePoints_WC_MLS_Response(pointInDB);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoint(@PathVariable String id) {
        Point point = pointService.getById(id);
        if (point == null) {
            throw new NotFoundException("Point not found");
        }
        pointService.delete(point);
        return ResponseEntity.noContent().build();
    }
    
}

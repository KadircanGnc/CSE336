package com.kentkart.api.line.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.exception.BadParametersException;
import com.kentkart.api.exception.NotFoundException;
import com.kentkart.api.line.Line;
import com.kentkart.api.line.service.LineService;
import com.kentkart.api.line.xaction.CreateLine_WC_MLS_Request;
import com.kentkart.api.line.xaction.CreateLine_WC_MLS_Response;
import com.kentkart.api.line.xaction.GetLines_WC_MLS_Response;
import com.kentkart.api.line.xaction.UpdateLine_WC_MLS_Request;
import com.kentkart.api.line.xaction.UpdateLine_WC_MLS_Response;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/lines")
@RequiredArgsConstructor
public class LineController {
    
    private final LineService lineService;

    @GetMapping
    public ResponseEntity<Page<GetLines_WC_MLS_Response>> getLines(Pageable pageable) {
        return ResponseEntity.ok(lineService.getAll(pageable).map(GetLines_WC_MLS_Response::new));
    }

    @PostMapping
    public ResponseEntity<CreateLine_WC_MLS_Response> createLine(@RequestBody CreateLine_WC_MLS_Request request) {
        Line lineInDB = lineService.getByLineCode(request.getLineCode());
        if (lineInDB != null) {
            throw new BadParametersException("Line with lineCode " + request.getLineCode() + " already exists");
        }
        lineInDB = new Line();
        lineInDB.setLineCode(request.getLineCode());
        lineInDB.setLineCodeRepresentation(request.getLineCodeRepresentation());
        lineInDB = lineService.create(lineInDB);
        return ResponseEntity.ok(new CreateLine_WC_MLS_Response(lineInDB));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLines_WC_MLS_Response> getLine(@PathVariable String id) {
        Line line = lineService.getById(id);
        if (line == null) {
            throw new NotFoundException("Line with id " + id + " not found");
        }
        return ResponseEntity.ok(new GetLines_WC_MLS_Response(line));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UpdateLine_WC_MLS_Response> updateLine(@PathVariable String id, @RequestBody UpdateLine_WC_MLS_Request request) {
        Line line = lineService.getById(id);
        if (line == null) {
            throw new NotFoundException("Line with id " + id + " not found");
        }
        line.setLineCode(request.getLineCode());
        line.setLineCodeRepresentation(request.getLineCodeRepresentation());
        line = lineService.create(line); // why create not update?
        return ResponseEntity.ok(new UpdateLine_WC_MLS_Response(line));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLine(@PathVariable String id) {
        Line line = lineService.getById(id);
        if (line == null) {
            throw new NotFoundException("Line with id " + id + " not found");
        }
        lineService.delete(id);
        return ResponseEntity.ok().build();
    }
}

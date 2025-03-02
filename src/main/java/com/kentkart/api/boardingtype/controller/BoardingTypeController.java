package com.kentkart.api.boardingtype.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.boardingtype.BoardingType;
import com.kentkart.api.boardingtype.service.BoardingTypeService;
import com.kentkart.api.boardingtype.xaction.CreateBoardingType_WC_MLS_Request;
import com.kentkart.api.boardingtype.xaction.CreateBoardingType_WC_MLS_Response;
import com.kentkart.api.boardingtype.xaction.GetBoardingTypes_WC_MLS_Response;
import com.kentkart.api.boardingtype.xaction.UpdateBoardingType_WC_MLS_Request;
import com.kentkart.api.boardingtype.xaction.UpdateBoardingType_WC_MLS_Response;
import com.kentkart.api.exception.BadParametersException;
import com.kentkart.api.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/boarding-types")
@RequiredArgsConstructor
public class BoardingTypeController {
    
    private final BoardingTypeService boardingTypeService;

    @GetMapping
    public ResponseEntity<Page<GetBoardingTypes_WC_MLS_Response>> getBoardingTypes(Pageable pageable) {
        return ResponseEntity.ok(boardingTypeService.getAll(pageable).map(GetBoardingTypes_WC_MLS_Response::new));
    }

    @PostMapping
    public ResponseEntity<CreateBoardingType_WC_MLS_Response> createBoardingType(@RequestBody CreateBoardingType_WC_MLS_Request request) {
        BoardingType boardingTypeInDB = boardingTypeService.getByName(request.getName());
        if (boardingTypeInDB != null) {
            throw new BadParametersException("BoardingType with name " + request.getName() + " already exists");
        }
        boardingTypeInDB = new BoardingType();
        boardingTypeInDB.setName(request.getName());
        boardingTypeInDB = boardingTypeService.create(boardingTypeInDB);
        return ResponseEntity.ok(new CreateBoardingType_WC_MLS_Response(boardingTypeInDB));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBoardingTypes_WC_MLS_Response> getBoardingType(@PathVariable String id) {
        BoardingType boardingType = boardingTypeService.getById(id);
        if (boardingType == null) {
            throw new NotFoundException("BoardingType with id " + id + " not found");
        }
        return ResponseEntity.ok(new GetBoardingTypes_WC_MLS_Response(boardingType));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UpdateBoardingType_WC_MLS_Response> updateBoardingType(@PathVariable String id, @RequestBody UpdateBoardingType_WC_MLS_Request request) {
        BoardingType boardingType = boardingTypeService.getById(id);
        if (boardingType == null) {
            throw new NotFoundException("BoardingType with id " + id + " not found");
        }
        boardingType.setName(request.getName());
        boardingType = boardingTypeService.create(boardingType);
        return ResponseEntity.ok(new UpdateBoardingType_WC_MLS_Response(boardingType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardingType(@PathVariable String id) {
        BoardingType boardingType = boardingTypeService.getById(id);
        if (boardingType == null) {
            throw new NotFoundException("BoardingType with id " + id + " not found");
        }
        boardingTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}

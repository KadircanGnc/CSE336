package com.kentkart.api.stats;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kentkart.api.boarding.Boarding;
import com.kentkart.api.boarding.service.BoardingService;
import com.kentkart.api.xaction.GetBoardingStats_WC_MLS_Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/stats")
@RequiredArgsConstructor
public class StatsController {

  public final BoardingService boardingService;

  @GetMapping("/boardings")
  public ResponseEntity<List<GetBoardingStats_WC_MLS_Response>> getBoardingStats() {

    Page<Boarding> boardings = boardingService.getAll(null, null, null, null, null, Pageable.unpaged());
    List<GetBoardingStats_WC_MLS_Response> boardingStats = new ArrayList<>();

    for (Boarding boarding : boardings) {
      String passengerId = boarding.getPassengerId();
      Integer boardingCount = (int) boardings.stream().filter(b -> b.getPassengerId().equals(passengerId)).count();

      GetBoardingStats_WC_MLS_Response boardingStat = boardingStats.stream()
          .filter(b -> b.getPassengerId().equals(passengerId)).findFirst().orElse(null);

      if (boardingStat == null) {
        boardingStat = new GetBoardingStats_WC_MLS_Response();
        boardingStat.setPassengerId(passengerId);
        boardingStat.setBoardingCount(boardingCount);
        boardingStats.add(boardingStat);
      }
    }

    return ResponseEntity.ok(boardingStats);
  }
}

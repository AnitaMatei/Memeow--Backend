package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.model.RankingsResponse;
import com.callbackcats.memeow.model.dto.LeaderboardDTO;
import com.callbackcats.memeow.service.LeaderboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rankings/")
public class LeaderboardController {
    LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("all")
    public ResponseEntity<RankingsResponse> getRankings(){
        return ResponseEntity.ok(leaderboardService.getRankings());
    }

    @GetMapping("{placement}")
    public ResponseEntity<LeaderboardDTO> getSpecificRanking(@PathVariable Integer placement){
        return ResponseEntity.ok(leaderboardService.getSpecificRanking(placement));
    }
}

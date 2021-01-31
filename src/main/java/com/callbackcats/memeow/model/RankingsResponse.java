package com.callbackcats.memeow.model;

import com.callbackcats.memeow.model.dto.LeaderboardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RankingsResponse {
    List<LeaderboardDTO> entries;
}

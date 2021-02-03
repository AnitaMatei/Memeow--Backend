package com.callbackcats.memeow.service;

import com.callbackcats.memeow.exception.RankingNotFoundException;
import com.callbackcats.memeow.model.RankingsResponse;
import com.callbackcats.memeow.model.UserLeaderboardResponse;
import com.callbackcats.memeow.model.dto.LeaderboardDTO;
import com.callbackcats.memeow.model.entity.Leaderboard;
import com.callbackcats.memeow.model.entity.Meme;
import com.callbackcats.memeow.model.entity.RecentMeme;
import com.callbackcats.memeow.model.entity.User;
import com.callbackcats.memeow.repository.LeaderboardRepository;
import com.callbackcats.memeow.repository.RecentMemeRepository;
import com.callbackcats.memeow.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableScheduling
public class LeaderboardService {
    LeaderboardRepository leaderboardRepository;
    RecentMemeRepository recentMemeRepository;
    MemeService memeService;
    ModelMapper modelMapper;
    UserRepository userRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository, RecentMemeRepository recentMemeRepository, MemeService memeService, ModelMapper modelMapper, UserRepository userRepository) {
        this.leaderboardRepository = leaderboardRepository;
        this.recentMemeRepository = recentMemeRepository;
        this.memeService = memeService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public RankingsResponse getRankings() {
        List<Leaderboard> entries = leaderboardRepository.findAll();
        List<LeaderboardDTO> responseEntries = entries.stream()
                .limit(Integer.min(10, (int)userRepository.count()))
                .map(leaderboard -> {
                    LeaderboardDTO dto = modelMapper.map(leaderboard, LeaderboardDTO.class);

                    dto.setUserResponse(modelMapper.map(leaderboard.getUserByUserId(), UserLeaderboardResponse.class));
                    return dto;
                })
                .collect(Collectors.toList());

        return new RankingsResponse(responseEntries);
    }

    public LeaderboardDTO getSpecificRanking(Integer placement) {
        Leaderboard ranking = leaderboardRepository.findByLeaderboardPlace(placement).orElseThrow(RankingNotFoundException::new);
        LeaderboardDTO responseEntry = modelMapper.map(ranking,LeaderboardDTO.class);
        responseEntry.setUserResponse(modelMapper.map(ranking.getUserByUserId(),UserLeaderboardResponse.class));
        return responseEntry;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void reevaluateRanks() {
        Map<User, Integer> pointsPerUser = new HashMap<>();
        List<Leaderboard> placements = leaderboardRepository.findAll().stream().sorted(Comparator.comparing(Leaderboard::getLeaderboardPlace)).collect(Collectors.toList());

        List<RecentMeme> recentMemes = recentMemeRepository.findAll();
        recentMemes.forEach(recentMeme -> {
            User user = recentMeme.getMeme().getUser();
            Meme meme = recentMeme.getMeme();
            if (pointsPerUser.containsKey(user))
                pointsPerUser.put(user, pointsPerUser.get(user) + meme.getReactionCount());
            else pointsPerUser.put(user, meme.getReactionCount());
        });

        Integer[] idx = new Integer[]{0};
        pointsPerUser.entrySet().stream().sorted(Map.Entry.<User,Integer>comparingByValue().reversed())
                .limit(10).forEach(userPointsEntry -> {
            placements.get(idx[0]).setUserByUserId(userPointsEntry.getKey());
            idx[0]++;
        });

        leaderboardRepository.saveAll(placements);
    }

}

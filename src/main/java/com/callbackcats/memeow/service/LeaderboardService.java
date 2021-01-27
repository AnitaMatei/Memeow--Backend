package com.callbackcats.memeow.service;

import com.callbackcats.memeow.model.entity.Leaderboard;
import com.callbackcats.memeow.model.entity.Meme;
import com.callbackcats.memeow.model.entity.RecentMeme;
import com.callbackcats.memeow.model.entity.User;
import com.callbackcats.memeow.repository.LeaderboardRepository;
import com.callbackcats.memeow.repository.RecentMemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableScheduling
public class LeaderboardService {
    LeaderboardRepository leaderboardRepository;
    RecentMemeRepository recentMemeRepository;
    MemeService memeService;

    public LeaderboardService(LeaderboardRepository leaderboardRepository, RecentMemeRepository recentMemeRepository, MemeService memeService) {
        this.leaderboardRepository = leaderboardRepository;
        this.recentMemeRepository = recentMemeRepository;
        this.memeService = memeService;
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

        pointsPerUser.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .limit(10).forEach(userPointsEntry -> {
                    placements.get(idx[0]).setUserByUserId(userPointsEntry.getKey());
                    idx[0]++;
        });

        leaderboardRepository.saveAll(placements);
    }
}

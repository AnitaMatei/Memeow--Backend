package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaderboardRepository  extends JpaRepository<Leaderboard,Integer> {
    Optional<Leaderboard> findByLeaderboardPlace(Integer place);
}

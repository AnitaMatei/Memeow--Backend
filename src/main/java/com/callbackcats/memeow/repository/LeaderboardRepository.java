package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardRepository  extends JpaRepository<Leaderboard,Integer> {
}

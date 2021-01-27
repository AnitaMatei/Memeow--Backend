package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.RecentMeme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentMemeRepository extends JpaRepository<RecentMeme, Integer> {
}

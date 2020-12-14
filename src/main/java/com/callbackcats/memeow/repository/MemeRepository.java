package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemeRepository extends JpaRepository<Meme,Integer> {
    Optional<Meme> findByMemeId(Integer id);
    Optional<Meme> findByMemeBusinessId(String id);

}

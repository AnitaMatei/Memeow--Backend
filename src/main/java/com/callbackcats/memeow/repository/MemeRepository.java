package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.Meme;
import com.callbackcats.memeow.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemeRepository extends JpaRepository<Meme,Integer> {
    Optional<Meme> findByMemeId(Integer id);
    Optional<Meme> findByMemeBusinessId(String id);
    Optional<Meme> findFirstByUserOrderByDateTimeUtcDesc(User user);
    List<Meme> findAllByMemeBusinessIdIn(List<String> uuids);
    List<Meme> findAllByDateTimeUtcAfter(Timestamp lastDay);
}

package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.Level;
import com.callbackcats.memeow.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level,Integer> {
    Optional<Level> findByUser(User user);
}

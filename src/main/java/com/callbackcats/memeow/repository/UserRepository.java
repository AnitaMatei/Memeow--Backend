package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserId(Integer id);
    Optional<User> findByEmail(String email);
    Optional<User> findByProfileUuid(String profileUuid);
}

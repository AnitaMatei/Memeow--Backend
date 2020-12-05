package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
    Optional<Tag> findByTagId(Integer id);
}

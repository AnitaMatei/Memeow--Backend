package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.Template;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
    Optional<Template> findByTemplateId(Integer id);
    List<Template> findByIgnoreCaseTemplateNameContainingAndMinRequiredLevelEquals(String name, Integer level, Pageable pageable);
    List<Template> findByMinRequiredLevelEquals(Integer level, Pageable pageable);
}

package com.callbackcats.memeow.repository;

import com.callbackcats.memeow.model.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
    Optional<Template> findByTemplateId(Integer id);
}

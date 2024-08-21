package com.example.personal_search_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.personal_search_engine.model.PageUrl;

@Repository
@EnableJpaRepositories
public interface PageUrlRepository extends JpaRepository<PageUrl, Integer> {

    PageUrl findByName(String name);
    PageUrl findByUrl(String url);
    List<String> findByTokens(String token);
}

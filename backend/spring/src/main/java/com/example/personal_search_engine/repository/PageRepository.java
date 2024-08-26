package com.example.personal_search_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.personal_search_engine.model.Page;

@Repository
@EnableJpaRepositories
public interface PageRepository extends JpaRepository<Page, Integer> {

    /*
     * Use JPQL native query for optimal query. See 'Page.java' for more details.
     * 
     */
    @Query(value = "SELECT DISTINCT p.page_page_id FROM page_tokens as p WHERE p.page_tokens = :token", nativeQuery = true)
    List<Integer> findPageIdByToken(String token);

    Page getById(Integer id);

}

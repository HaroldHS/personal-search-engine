package com.example.personal_search_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.example.personal_search_engine.model.Page;

@Repository
@EnableJpaRepositories
public interface PageRepository extends JpaRepository<Page, Integer> {

    /*
     * Use JPQL native query for optimal query. See 'Page.java' for more details.
     * 
     * For TF, which equals to log(countTokenInPage() / totalOfTokensInPage())
     * 
     */
    @Query(value = "SELECT COUNT(p.page_tokens) FROM page_tokens as p WHERE p.page_page_id = :pageId AND p.page_tokens = :token", nativeQuery = true)
    Integer countTokenInPage(Integer pageId, String token);

    @Query(value = "SELECT COUNT(p.page_tokens) FROM page_tokens as p WHERE p.page_page_id = :pageId", nativeQuery = true)
    Integer totalOfTokensInPage(Integer pageId);

    /*
     * For IDF, which equals to log(getAllPageId() / countPageIdByToken())
     * 
     */
    @Query(value = "SELECT COUNT(p.page_id) FROM page as p", nativeQuery = true)
    Integer getAllPageId();

    @Query(value = "SELECT COUNT(p.page_page_id) FROM page_tokens as p WHERE p.page_tokens = :token", nativeQuery = true)
    Integer countPageIdByToken(String token);

    @Query(value = "SELECT DISTINCT p.page_page_id FROM page_tokens as p WHERE p.page_tokens = :token", nativeQuery = true)
    List<Integer> findPageIdByToken(String token);

    @NonNull
    Page getById(@NonNull Integer id);

}

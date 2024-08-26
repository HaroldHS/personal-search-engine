package com.example.personal_search_engine.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "page")
public class Page {

    @Id
    @Column(name = "page_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "page id can't be null")
    private Integer id;

    @Column(name = "page_name", length = 512)
    @NotEmpty(message = "page name can't be empty")
    private String name;

    @Column(name = "page_url", length = 1024)
    @NotEmpty(message = "page url can't be empty")
    private String url;

    @Column(name = "page_url_hash", length = 256)
    private String md5;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @Column(name = "page_tokens", nullable = false)
    private List<String> tokens;

    public Page() {
    }

    public Page(Integer id, String name, String url, String md5, List<String> tokens) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.md5 = md5;
        this.tokens = tokens;
    }

    /*
     * >>> Documentation <<<
     * 
     * JPARepository stores Page (MySQL) as below:
     * 
     * page_url_tokens
     *  |
     *  |-> page_page_id (means page.page_id)
     *  |-> page_tokens
     * 
     * page
     *  |
     *  |-> page_id
     *  |-> page_url_hash
     *  |-> page_name
     *  |-> page_url
     * 
     */

}

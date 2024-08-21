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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "url")
public class PageUrl {

    @Id
    @Column(name = "url_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "page_name", length = 512)
    private String name;

    @Column(name = "page_url", length = 1024)
    private String url;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @Column(name = "page_tokens", nullable = false)
    private List<String> tokens;

    public PageUrl() {
    }

    public PageUrl(Integer id, String name, String url, List<String> tokens) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.tokens = tokens;
    }

}

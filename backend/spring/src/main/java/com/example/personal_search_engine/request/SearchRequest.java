package com.example.personal_search_engine.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {

    private String query;

    public SearchRequest() {
    }

    public SearchRequest(String query) {
        this.query = query;
    }

}

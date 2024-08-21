package com.example.personal_search_engine.service;

import java.util.HashMap;

import com.example.personal_search_engine.request.SearchRequest;

public interface SearchService {
    HashMap<String, Object> queryResult(SearchRequest searchRequest);
}

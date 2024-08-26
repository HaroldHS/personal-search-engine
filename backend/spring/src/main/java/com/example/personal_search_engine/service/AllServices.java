package com.example.personal_search_engine.service;

import java.util.HashMap;
import java.util.List;

import com.example.personal_search_engine.request.AddUrlRequest;
import com.example.personal_search_engine.request.SearchRequest;

public interface AllServices {
    List<HashMap<String, String>> queryResult(SearchRequest searchRequest);
    HashMap<String, Object> addURL(AddUrlRequest addUrlRequest);
}

package com.example.personal_search_engine.service;

import java.util.HashMap;

import com.example.personal_search_engine.request.AddUrlRequest;
import com.example.personal_search_engine.request.SearchRequest;

public interface AllServices {
    HashMap<String, Object> queryResult(SearchRequest searchRequest);
    HashMap<String, Object> addURL(AddUrlRequest addUrlRequest);
}

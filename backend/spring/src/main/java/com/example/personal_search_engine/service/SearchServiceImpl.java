package com.example.personal_search_engine.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.personal_search_engine.repository.PageUrlRepository;
import com.example.personal_search_engine.request.SearchRequest;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private PageUrlRepository pageUrlRepository;

    @Override
    public HashMap<String, Object> queryResult(SearchRequest searchRequest) {
        HashMap<String, Object> result = new HashMap<String, Object>();

        return result;
    }
}

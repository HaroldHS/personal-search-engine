package com.example.personal_search_engine.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.personal_search_engine.request.SearchRequest;

@RestController
public class MainController {

    @GetMapping("/api/v1/")
    public ResponseEntity<Object> home() {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("message", "Welcome to Personal Search Engine API");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/search")
    public ResponseEntity<Object> search(@RequestBody SearchRequest searchRequest) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("query", searchRequest.getQuery());

        return ResponseEntity.ok(response);
    }
}

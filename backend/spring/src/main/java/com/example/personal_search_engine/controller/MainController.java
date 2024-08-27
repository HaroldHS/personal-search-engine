package com.example.personal_search_engine.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personal_search_engine.request.AddUrlRequest;
import com.example.personal_search_engine.request.SearchRequest;
import com.example.personal_search_engine.service.AllServices;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    @Autowired
    private AllServices allServices;

    @GetMapping("/")
    public ResponseEntity<Object> home() {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("message", "Welcome to Personal Search Engine API");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> search(@RequestBody SearchRequest searchRequest) {
        List<HashMap<String, String>> response = allServices.queryResult(searchRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add_url")
    public ResponseEntity<Object> addUrl(@RequestBody AddUrlRequest addUrlRequest) {
        HashMap<String, Object> addUrlResponse = allServices.addURL(addUrlRequest);
        return ResponseEntity.ok(addUrlResponse);

    }
}

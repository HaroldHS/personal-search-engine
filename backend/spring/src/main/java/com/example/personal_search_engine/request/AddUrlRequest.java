package com.example.personal_search_engine.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUrlRequest {

    private String url;

    public AddUrlRequest() {
    }

    public AddUrlRequest(String url) {
        this.url = url;
    }
}

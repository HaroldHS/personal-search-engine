package com.example.personal_search_engine.helper;

// import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TFIDF {

    public String[] getTokens(String content);
    public List<String> listOfUniqueTokens(String[] tokens);
    public HashMap<String, Integer> invertIndexing(String[] tokens);
}

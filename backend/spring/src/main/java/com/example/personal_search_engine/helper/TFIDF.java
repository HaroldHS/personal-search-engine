package com.example.personal_search_engine.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TFIDF {

    public List<String> getTokens(String content);
    public List<String> listOfUniqueTokens(List<String> tokens);
    public HashMap<String, ArrayList<Integer>> invertIndexing(List<String> tokens);
    public Float countTFIDF(Integer countTokenInPage, Integer totalOfTokensInPage, Integer getAllPageId, Integer countPageIdByToken);
}

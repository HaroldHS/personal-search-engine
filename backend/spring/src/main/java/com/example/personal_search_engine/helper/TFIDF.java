package com.example.personal_search_engine.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TFIDF {

    public static List<String> listOfUniqueTokens(String[] tokens) {
        Set<String> setOfTokens = new HashSet<String>();
        
        for(String s: tokens) {
            setOfTokens.add(s);
        }

        List<String> result = new ArrayList<String>(setOfTokens);
        return result;
    }

    public static HashMap<String, ArrayList<Integer>> invertIndexing(String[] tokens) {
        HashMap<String, ArrayList<Integer>> result = new HashMap<String, ArrayList<Integer>>();
        Integer tokensLength = tokens.length;

        for(Integer i=0; i<tokensLength; i++) {
            if(result.get(tokens[i]) == null) {
                ArrayList<Integer> invertIndexContainer = new ArrayList<Integer>();
                invertIndexContainer.add(i);
                result.put(tokens[i], invertIndexContainer);

                // Instead of saving the token position, just save the token occurence
                // Integer tokenOccurence = 0;
                // tokenOccurence += 1;
                // result.put(tokens[i], tokenOccurence);
            } else {
                ArrayList<Integer> container = result.get(tokens[i]);
                container.add(i);
                result.put(tokens[i], container);

                // Instead of saving the token position, just save the token occurence
                // Integer tokenOccurence = result.get(tokens[i]);
                // tokenOccurence += 1;
                // result.put(tokens[i], tokenOccurence);
            }
        }

        return result;
    }

    public static Float countTFIDF(Integer countTokenInPage, Integer totalOfTokensInPage, Integer getAllPageId, Integer countPageIdByToken) {
        Float tf = (float) countTokenInPage / totalOfTokensInPage;
        float floatDivision = (float) getAllPageId / countPageIdByToken;
        Float idf = (float) Math.log(floatDivision);
        return tf*idf;
    }
}

package com.example.personal_search_engine.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TFIDFImpl implements TFIDF {

    public String[] getTokens(String content) {
        String[] result = content.split("[,\\.\\s]");
        return result;
    }

    public List<String> listOfUniqueTokens(String[] tokens) {
        Set<String> setOfTokens = new HashSet<String>();
        
        for(String s: tokens) {
            setOfTokens.add(s);
        }

        List<String> result = new ArrayList<String>(setOfTokens);
        return result;
    }

    public HashMap<String, Integer> invertIndexing(String[] tokens) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        Integer tokensLength = tokens.length;

        for(Integer i=0; i<tokensLength; i++) {
            // Instead of saving the token position, just save the token occurence
            if(result.get(tokens[i]) == null) {
                // ArrayList<Integer> invertIndexContainer = new ArrayList<Integer>();
                // invertIndexContainer.add(i);
                // result.put(tokens[i], invertIndexContainer);

                Integer tokenOccurence = 0;
                tokenOccurence += 1;
                result.put(tokens[i], tokenOccurence);
            } else {
                // ArrayList<Integer> container = result.get(tokens[i]);
                // container.add(i);
                // result.put(tokens[i], container);

                Integer tokenOccurence = result.get(tokens[i]);
                tokenOccurence += 1;
                result.put(tokens[i], tokenOccurence);
            }
        }

        return result;
    }

    public Float countTFIDF(Integer countTokenInPage, Integer totalOfTokensInPage, Integer getAllPageId, Integer countPageIdByToken) {
        Float tf = (float) countTokenInPage / totalOfTokensInPage;
        Float idf = (float) Math.log(getAllPageId / countPageIdByToken);
        return tf*idf;
    }
}

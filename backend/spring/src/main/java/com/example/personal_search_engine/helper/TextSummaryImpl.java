package com.example.personal_search_engine.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class TextSummaryImpl implements TextSummary {

    public String getTextSummary(String JSONFileName, String TextFileName, String[] tokens) {

        StringBuffer result = new StringBuffer();

        try {

            // TODO: Refactor the code below in order to prevent race condition
            File jsonFile = new File(JSONFileName);
            FileInputStream jsonFIS = new FileInputStream(jsonFile);
            byte[] jsonData = new byte[(int) jsonFile.length()];
            jsonFIS.read(jsonData);
            jsonFIS.close();

            String jsonContentString = new String(jsonData, "UTF-8");
            JSONArray jsonContentArray = new JSONArray(jsonContentString);

            // Index 4 = JSON invert indexing result
            String invertIndexString = jsonContentArray.get(4).toString();
            JSONObject jsonInvertIndex = new JSONObject(invertIndexString);

            List<Integer> minIndexList = new ArrayList<Integer>();
            // For each token, find the smallest index of each token/term and construct it later for a range.
            for(String token: tokens) {
                try {
                    // TODO: Implement text summarization
                    // System.out.println(jsonInvertIndex.get(token));

                    List<Integer> listOfTokenInvertindex = new ArrayList<Integer>();
                    JSONArray arrayOfTokenInvertIndex = new JSONArray(jsonInvertIndex.get(token).toString());
                    int il = arrayOfTokenInvertIndex.length();

                    for (int i=0; i<il; i++) {
                        listOfTokenInvertindex.add((Integer) arrayOfTokenInvertIndex.get(i));
                    }

                    minIndexList.add(Collections.min(listOfTokenInvertindex));

                }
                // In case token is not inside the invert indexing object, just continue
                catch (Exception e) {
                    continue;
                }
            }

            // System.out.println(minIndexList);
            int startingIndex = Collections.min(minIndexList);
            int counter = 0;

            // Open the intended page content file (.txt) and take the first 20 words starting from startingIndex
            File textContentFile = new File(TextFileName);
            Scanner scan = new Scanner(textContentFile);
            scan.useDelimiter("[^a-zA-Z0-9\\s]+");

            // First, make the scanner jump to startingIndex
            while (counter < startingIndex) {
                // Do nothing, just update the scanner state
                scan.next();
            }

            // Then, Reset the counter
            counter = 0;
            // At last, take the first 20 words starting from word with index "startingIndex"
            while (scan.hasNextLine() && ((startingIndex + counter) < (startingIndex + 20))) {
                result.append(scan.next());
                counter += 1;
            }
            scan.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return result.toString();
    } 

}

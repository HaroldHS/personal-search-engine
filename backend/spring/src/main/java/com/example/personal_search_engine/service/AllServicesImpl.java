package com.example.personal_search_engine.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
// import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.personal_search_engine.helper.Hashing;
import com.example.personal_search_engine.helper.TFIDF;
import com.example.personal_search_engine.helper.TFIDFImpl;
import com.example.personal_search_engine.model.PageUrl;
import com.example.personal_search_engine.repository.PageUrlRepository;
import com.example.personal_search_engine.request.AddUrlRequest;
import com.example.personal_search_engine.request.SearchRequest;

@Service
public class AllServicesImpl implements AllServices {

    static Logger logger = Logger.getLogger(AllServicesImpl.class.getName());

    @Value("${personal-search-engine.tfidf-path}") 
    private String tfidfPath;

    @Autowired
    private PageUrlRepository pageUrlRepository;

    @Override
    public HashMap<String, Object> queryResult(SearchRequest searchRequest) {
        HashMap<String, Object> result = new HashMap<String, Object>();

        return result;
    }

    @Override
    public HashMap<String, Object> addURL(AddUrlRequest addUrlRequest) {

        Boolean errorFlag = false;
        Document doc;
        StringBuffer resultMessage = new StringBuffer();
        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(System.getProperty("user.dir").concat("\\logging.properties")));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        logger.setLevel(Level.INFO);
        logger.addHandler(new ConsoleHandler());

        try {
            if(addUrlRequest.getUrl().startsWith("http://") || addUrlRequest.getUrl().startsWith("https://")) {
                doc = Jsoup.connect(addUrlRequest.getUrl()).get();
            } else {
                File fileInput = new File(addUrlRequest.getUrl());
                doc = Jsoup.parse(fileInput, "UTF-8");
            }

            try {
                String content = doc.body().text();
                TFIDF tfidf = new TFIDFImpl();

                // 1. Obtain tokens
                String[] tokens = tfidf.getTokens(content);
                List<String> uniqueTokens = tfidf.listOfUniqueTokens(tokens);
                // 2. Invert indexing
                HashMap<String, Integer> invertIndex = tfidf.invertIndexing(tokens);
                // 3. Generate hash for URL
                String urlHash = Hashing.getMD5Hash(addUrlRequest.getUrl());
                // 4. Generate JSON file
                JSONArray ja = new JSONArray();
                ja.put(addUrlRequest.getName());
                ja.put(addUrlRequest.getUrl());
                ja.put(urlHash);
                ja.put(uniqueTokens);
                ja.put(invertIndex);
                // 5. Save PageUrl model into database via repository
                PageUrl pageUrl = new PageUrl();
                pageUrl.setName(addUrlRequest.getName());
                pageUrl.setUrl(addUrlRequest.getUrl());
                pageUrl.setMd5(urlHash);
                pageUrl.setTokens(uniqueTokens);
                pageUrlRepository.save(pageUrl);
                // 6. Create JSON file for tfidf and save it into specified location with hashing as its name
                // TODO: Refactor the code below in order to prevent race condition
                FileWriter jsonFile = new FileWriter(this.tfidfPath.concat("/"+urlHash+".json"));
                jsonFile.write(ja.toString());
                jsonFile.close();

                resultMessage.append("tfidf successfull");
            } catch (Exception e) {
                errorFlag = true;
                logger.log(Level.INFO, "Can't obtain text from document");
            }
        } catch (Exception e) {
            errorFlag = true;
            logger.log(Level.INFO, "Can't obtain document via URL");
        }

        if(errorFlag) {
            result.put("status", "error");
            result.put("message", "Can't obtain document via URL / text from document");
        } else {
            result.put("status", "success");
            result.put("message", resultMessage);
        }

        return result;
    }
}

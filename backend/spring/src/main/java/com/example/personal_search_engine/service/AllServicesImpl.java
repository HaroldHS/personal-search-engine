package com.example.personal_search_engine.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.personal_search_engine.repository.PageUrlRepository;
import com.example.personal_search_engine.request.AddUrlRequest;
import com.example.personal_search_engine.request.SearchRequest;

@Service
public class AllServicesImpl implements AllServices {

    static Logger logger = Logger.getLogger(AllServicesImpl.class.getName());

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
                resultMessage.append(content);
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

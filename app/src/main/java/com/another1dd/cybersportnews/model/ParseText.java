package com.another1dd.cybersportnews.model;


import android.os.AsyncTask;

import com.another1dd.cybersportnews.adapters.TestRecyclerViewAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class ParseText extends AsyncTask<String, Void, HashMap<String, String>> {

    @Override
    protected HashMap<String, String> doInBackground(String... params) {

        HashMap<String, String> map = new HashMap<>();


        try {

            Document document = Jsoup.connect(params[0]).get();
            map.clear();

            Elements element = document.select(".lead");
            Elements elementMain = document.select(".vp-news-text");
            Elements elementsPanel = document.select(".text.b-match-text-fix");
            Elements elementsBlogs = document.select(".community_text");
            for (Element elements : element) {
                map.put(TestRecyclerViewAdapter.LEAD, elements.text());
            }

            for (Element elements : elementMain) {
                map.put(TestRecyclerViewAdapter.MAIN_NEWS_TEXT, elements.text());
            }

            for (Element elements : elementsPanel) {
                map.put(TestRecyclerViewAdapter.PANEL_BODY, elements.text());
            }

            for (Element elements : elementsBlogs) {
                map.put(TestRecyclerViewAdapter.BLOG_TEXT, elements.text());
            }
        } catch (IOException e) {
            map.put(TestRecyclerViewAdapter.PANEL_BODY, "Try: \n -Turn on or enable mobile data \n -Find WiFi Hotspot in your area \n\n " +
                    "Попробуйте:\n -Включить передачу данных\n -Найти Wifi поблизости");
            e.printStackTrace();
        }

        return map;
    }
}
package com.another1dd.cybersportnews_materialviewpager;


import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ParseText extends AsyncTask<String, Void, HashMap<String,String>>
{

    @Override
    protected HashMap<String,String> doInBackground(String... params) {

        HashMap<String,String> map = new HashMap<>();


        try {

            Document document = Jsoup.connect(params[0]).get();
            map.clear();

            Elements element = document.select(".lead");
            Elements elementMain = document.select(".vp-news-text");
            Elements elementsPanel = document.select(".text.b-match-text-fix");
            for (Element elements : element)
            {
                map.put(TestRecyclerViewAdapter.LEAD,elements.text());
            }

            for (Element elements : elementMain)
            {
                map.put(TestRecyclerViewAdapter.MAIN_NEWS_TEXT,elements.text());
            }

            for (Element elements : elementsPanel)
            {
                map.put(TestRecyclerViewAdapter.PANEL_BODY, elements.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
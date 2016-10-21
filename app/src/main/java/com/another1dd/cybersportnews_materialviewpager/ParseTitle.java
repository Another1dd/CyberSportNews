package com.another1dd.cybersportnews_materialviewpager;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class ParseTitle extends AsyncTask<String, Void, LinkedHashMap<String,String>>
{

    @Override
    protected LinkedHashMap<String, String> doInBackground(String... params) {

        LinkedHashMap<String,String> hashMap = new LinkedHashMap<>();

        try {
            Document document = Jsoup.connect(params[0]).get();
            Elements elements = document.select(".b-news-list-item__title");
            for (Element element : elements)
            {
                Element element1 = element.select("a[href]").first();

                hashMap.put(element.text(), element1.attr("abs:href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hashMap;
    }
}
package com.another1dd.cybersportnews_materialviewpager;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashMap;


class ParseTitle extends AsyncTask<String, Void, LinkedHashMap<String[], String>> {

    @Override
    protected LinkedHashMap<String[], String> doInBackground(String... params) {

        LinkedHashMap<String[], String> hashMap = new LinkedHashMap<>();

        try {
            Document document = Jsoup.connect(params[0]).get();
            Elements elements = document.select(".b-news-list-item__media-body");

            for (Element element : elements) {
                Element title = element.select(".b-news-list-item__title").first();
                Element time = element.select(".time.b-news-list-item__time.grey-text").first();
                String[] temp = {title.text(), time.text()};
                Element element1 = element.select("a[href]").first();

                hashMap.put(temp, element1.attr("abs:href"));
            }
        } catch (IOException e) {
            hashMap.put(new String[]{"There is no internet connection", "Нет подключения к интернету"}, "http://www.cybersport.ru/news/");
            e.printStackTrace();
        }

        return hashMap;
    }
}
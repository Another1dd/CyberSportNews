package com.another1dd.cybersportnews_materialviewpager;


import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ParseText extends AsyncTask<String, Void, String>
{

    @Override
    protected String doInBackground(String... params) {

        String attr = " ";


        try {

            Document document = Jsoup.connect(params[0]).get();
            Element element = document.select(".lead").first();

            attr = element.text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return attr;
    }
}
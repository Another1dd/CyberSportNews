package com.another1dd.cybersportnews_materialviewpager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


public class ParseImg extends AsyncTask<String, Void, Bitmap>
{

    @Override
    protected Bitmap doInBackground(String... params) {

        String imageUrl = "";


        try {

            Document document = Jsoup.connect(params[0]).get();
            Element fearuteImg = document.select("").first();
            String temp = fearuteImg.getElementsByAttribute("style").toString();

            imageUrl = temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap imageOne = getImageBitmap(imageUrl);
        return imageOne;
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {

            // See what we are getting
            Log.i(TAG, "" + url);

            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);

            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }
}
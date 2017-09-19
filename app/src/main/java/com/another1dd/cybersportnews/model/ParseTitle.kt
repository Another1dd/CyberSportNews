package com.another1dd.cybersportnews.model

import android.os.AsyncTask
import org.jsoup.Jsoup
import java.io.IOException
import java.util.*


class ParseTitle : AsyncTask<String, Void, LinkedHashMap<Array<String>, String>>() {

    override fun doInBackground(vararg params: String): LinkedHashMap<Array<String>, String> {

        val hashMap = LinkedHashMap<Array<String>, String>()

        try {
            val document = Jsoup.connect(params[0]).get()
            val elements = document.select(".b-news-list-item__media-body")

            for (element in elements) {
                val title = element.select(".b-news-list-item__title").first()
                val time = element.select(".time.b-news-list-item__time.grey-text").first()
                val temp = arrayOf(title.text(), time.text())
                val element1 = element.select("a[href]").first()

                hashMap.put(temp, element1.attr("abs:href"))
            }
        } catch (e: IOException) {
            hashMap.put(arrayOf("There is no internet connection", "Нет подключения к интернету"), "http://www.cybersport.ru/news/")
            e.printStackTrace()
        }

        return hashMap
    }
}
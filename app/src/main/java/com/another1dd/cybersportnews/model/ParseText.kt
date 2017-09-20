package com.another1dd.cybersportnews.model


import android.os.AsyncTask
import org.jsoup.Jsoup
import java.io.IOException
import java.util.*

class ParseText : AsyncTask<String, Void, HashMap<String, String>>() {
    override fun doInBackground(vararg params: String): HashMap<String, String> {

        val map = HashMap<String, String>()


        try {

            val document = Jsoup.connect(params[0]).get()
            map.clear()

            val element = document.select(".lead")
            val elementMain = document.select(".vp-news-text")
            val elementsPanel = document.select(".text.b-match-text-fix")
            val elementsBlogs = document.select(".community_text")
            for (elements in element) {
//                map.put(MainRecyclerViewAdapter.LEAD, elements.text())
            }

            for (elements in elementMain) {
//                map.put(MainRecyclerViewAdapter.MAIN_NEWS_TEXT, elements.text())
            }

            for (elements in elementsPanel) {
//                map.put(MainRecyclerViewAdapter.PANEL_BODY, elements.text())
            }

            for (elements in elementsBlogs) {
//                map.put(MainRecyclerViewAdapter.BLOG_TEXT, elements.text())
            }
        } catch (e: IOException) {
//            map.put(MainRecyclerViewAdapter.PANEL_BODY, "Try: \n -Turn on or enable mobile data \n -Find WiFi Hotspot in your area \n\n " + "Попробуйте:\n -Включить передачу данных\n -Найти Wifi поблизости")
            e.printStackTrace()
        }

        return map
    }
}
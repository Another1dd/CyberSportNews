package com.another1dd.cybersportnews.model

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import org.jsoup.Jsoup
import java.io.BufferedInputStream
import java.io.IOException
import java.net.URL


class ParseImg : AsyncTask<String, Void, Bitmap>() {
    override fun doInBackground(vararg params: String): Bitmap? {

        var imageUrl = ""


        try {

            val document = Jsoup.connect(params[0]).get()
            val fearuteImg = document.select("").first()
            val temp = fearuteImg.getElementsByAttribute("style").toString()

            imageUrl = temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"))


        } catch (e: IOException) {
            e.printStackTrace()
        }

        return getImageBitmap(imageUrl)
    }

    private fun getImageBitmap(url: String): Bitmap? {
        var bm: Bitmap? = null
        try {

            // See what we are getting
            Log.i(TAG, "" + url)

            val aURL = URL(url)
            val conn = aURL.openConnection()
            conn.connect()

            val `is` = conn.getInputStream()
            val bis = BufferedInputStream(`is`)
            bm = BitmapFactory.decodeStream(bis)

            bis.close()
            `is`.close()
        } catch (e: IOException) {
            Log.e(TAG, "Error getting bitmap", e)
        }

        return bm
    }
}
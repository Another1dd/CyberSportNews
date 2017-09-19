package com.another1dd.cybersportnews.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val handlerUi = Handler(Looper.getMainLooper())
        handlerUi.postDelayed({ this@SplashActivity.startMainActivity() }, 1000)
    }

    private fun startMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

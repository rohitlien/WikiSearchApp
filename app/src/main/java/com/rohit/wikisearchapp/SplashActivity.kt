package com.rohit.wikisearchapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rohit.wikisearchapp.ui.wikiSearch.WikiSearchActivity


class SplashActivity : AppCompatActivity() {

    var handler : Handler?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler()
        handler?.postDelayed({
            startActivity(Intent(this@SplashActivity,
                WikiSearchActivity::class.java))
            finish()
        },3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacksAndMessages(null)
    }
}

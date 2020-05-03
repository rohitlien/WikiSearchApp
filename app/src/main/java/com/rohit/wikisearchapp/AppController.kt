package com.rohit.wikisearchapp

import android.app.Application

/**
 * Created by rohit on 2020-03-26.
 */

class AppController : Application() {

    companion object {
        lateinit var instance: AppController
    }
    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}
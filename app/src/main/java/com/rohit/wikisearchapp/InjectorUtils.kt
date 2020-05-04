package com.rohit.wikisearchapp

import android.app.Application
import com.rohit.wikisearchapp.repos.WikiSearchRepository

object InjectorUtils {
    fun provideWikiViewModelFactory(application: Application): WikiSearchViewModelFactory {
        val repository = getRepository(application)
        return WikiSearchViewModelFactory(repository, application)
    }

    private fun getRepository(application: Application): WikiSearchRepository {
        return WikiSearchRepository(application)
    }

}
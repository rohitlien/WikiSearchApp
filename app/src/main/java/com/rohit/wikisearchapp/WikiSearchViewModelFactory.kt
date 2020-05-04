package com.rohit.wikisearchapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rohit.wikisearchapp.repos.WikiSearchRepository
import com.rohit.wikisearchapp.ui.wikiSearch.WikiSearchViewModel

/**
 * Created by rohit on 5/4/20.
 */
class WikiSearchViewModelFactory (private val repository: WikiSearchRepository,
                                  private val application: Application): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = WikiSearchViewModel(application,repository) as T

}
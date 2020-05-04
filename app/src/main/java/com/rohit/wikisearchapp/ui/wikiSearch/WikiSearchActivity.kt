package com.rohit.wikisearchapp.ui.wikiSearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.rohit.wikisearchapp.InjectorUtils
import com.rohit.wikisearchapp.R
import com.rohit.wikisearchapp.utils.WikiSearchTools
import kotlinx.android.synthetic.main.wiki_search_activity.*

class WikiSearchActivity : AppCompatActivity() {

    private val viewModel: WikiSearchViewModel by lazy {
        ViewModelProviders
            .of(this, InjectorUtils.provideWikiViewModelFactory(application))
            .get(WikiSearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wiki_search_activity)

        val navHostFragment = nav_host_fragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.navigation_search)
        val navController = navHostFragment.navController


        val destination = if (WikiSearchTools.checkInternetStatus(this)) R.id.wikiSearchFragment else R.id.wikiOfflineSearchListFragment
        navGraph.startDestination = destination
        navController.graph = navGraph

        viewModel.search("")

    }
}

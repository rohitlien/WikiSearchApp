package com.rohit.wikisearchapp.ui.wikiSearch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rohit.wikisearchapp.beans.WikiRoomData
import com.rohit.wikisearchapp.repos.WikiSearchRepository

class WikiSearchViewModel(application: Application, private val repository: WikiSearchRepository)
                            : AndroidViewModel(application) {

    val wikiRoomDatas: MutableLiveData<ArrayList<WikiRoomData>> = repository.getWikiSearchResults()
    val wikiRoomOfflineDatas: MutableLiveData<List<WikiRoomData>> = repository.getWikiSearchOfflineResults()

    fun search(searchTxt: String) {
        if(searchTxt.isEmpty())
            return
        repository.search(searchTxt)
    }

    fun getOfflineData() {
        repository.getTasks()
    }

}

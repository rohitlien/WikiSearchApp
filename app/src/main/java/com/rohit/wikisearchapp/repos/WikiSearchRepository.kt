package com.rohit.wikisearchapp.repos

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.rohit.wikisearchapp.beans.WikiRoomData
import com.rohit.wikisearchapp.beans.WikiSearchData
import com.rohit.wikisearchapp.beans.WikiSearchDataCollection
import com.rohit.wikisearchapp.dataBase.WikiSearchDatabase
import com.rohit.wikisearchapp.retrofit.RetrofitFactory
import com.rohit.wikisearchapp.retrofit.RetrofitServices
import com.rohit.wikisearchapp.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback

/**
 * Created by rohit on 5/4/20.
 */

class WikiSearchRepository(val context: Context?) {

    private val DB_NAME = "db_task"
    private var wikiSearchDatabase: WikiSearchDatabase? = null
    var wikiRoomDataMutableLiveDatas: MutableLiveData<ArrayList<WikiRoomData>> = MutableLiveData()
    var wikiRoomDataMutableOfflineDatas: MutableLiveData<List<WikiRoomData>> = MutableLiveData()

    init {
        wikiSearchDatabase = Room.databaseBuilder(
            context!!,
            WikiSearchDatabase::class.java, DB_NAME
        ).build()
    }

    fun insertWikiTask(datas: ArrayList<WikiSearchData>?) {
        var wikiRoomDataArrayList: ArrayList<WikiRoomData>? = null
        datas?.let {
            wikiRoomDataArrayList = ArrayList()
            for (wikiSearchData in datas) {
                wikiSearchData.apply {
                    val wikiRoomData = WikiRoomData(pageid,title,terms?.description.toString(),thumbnail?.source,false,index)
                    wikiRoomDataArrayList?.add(wikiRoomData)
                }
            }
            wikiRoomDataArrayList?.let { insertTask(it) }
        }

    }

    fun insertTask(datas: ArrayList<WikiRoomData>) {
        object : AsyncTask<Void?, Void?, Void?>() {

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                wikiRoomDataMutableLiveDatas.postValue(datas)
            }

            override fun doInBackground(vararg p0: Void?): Void? {
                for (data in datas) {
                    wikiSearchDatabase!!.daoAccess().insertTask(data)
                }
                return null
            }
        }.execute()
    }

    fun updateTask(wikiRoomData: WikiRoomData?) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg voids: Void?): Void? {
                wikiSearchDatabase!!.daoAccess().updateTask(wikiRoomData)
                return null
            }
        }.execute()
    }

    fun deleteTask(id: Int) {
        val task = getTask(id)
        if (task != null) {
            object : AsyncTask<Void?, Void?, Void?>() {
                override fun doInBackground(vararg voids: Void?): Void? {
                    wikiSearchDatabase!!.daoAccess().deleteTask(task.value)
                    return null
                }
            }.execute()
        }
    }

    fun deleteTask(wikiRoomData: WikiRoomData?) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg voids: Void?): Void? {
                wikiSearchDatabase!!.daoAccess().deleteTask(wikiRoomData)
                return null
            }
        }.execute()
    }

    fun getTask(id: Int): LiveData<WikiRoomData>? {
        return wikiSearchDatabase!!.daoAccess().getTask(id)
    }

    fun getTasks() {
        object : AsyncTask<Void?, Void?, List<WikiRoomData>?>() {

            override fun onPostExecute(result: List<WikiRoomData>?) {
                super.onPostExecute(result)
                wikiRoomDataMutableOfflineDatas.postValue(result)
            }

            override fun doInBackground(vararg p0: Void?): MutableList<WikiRoomData>? {
               return wikiSearchDatabase!!.daoAccess().fetchAllTasks()
            }
        }.execute()
    }


    fun getWikiSearchResults(): MutableLiveData<ArrayList<WikiRoomData>> {
        return wikiRoomDataMutableLiveDatas
    }
    fun getWikiSearchOfflineResults(): MutableLiveData<List<WikiRoomData>> {
        return wikiRoomDataMutableOfflineDatas
    }

    fun search(searchTxt: String) {
        val service = RetrofitFactory.getRetrofit().create(RetrofitServices::class.java)
        var baseUrl = AppConstants.BASE_SEARCH_URL
        baseUrl = baseUrl.replace("mySearchText", searchTxt)
        val call = service.search(baseUrl)
        call.enqueue(object : Callback<WikiSearchDataCollection> {
            override fun onFailure(call: Call<WikiSearchDataCollection>, t: Throwable) {
                Log.i("skncns",t.message)
            }

            override fun onResponse(call: Call<WikiSearchDataCollection>, response: retrofit2.Response<WikiSearchDataCollection>) {
                val data = response.body()
                insertWikiTask(data?.query?.pages)
            }

        })
    }
}
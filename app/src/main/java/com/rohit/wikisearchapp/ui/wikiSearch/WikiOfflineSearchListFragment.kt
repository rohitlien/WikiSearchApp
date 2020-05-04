package com.rohit.wikisearchapp.ui.wikiSearch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.rohit.wikisearchapp.R
import com.rohit.wikisearchapp.adapters.WikiSearchDataAdapter
import com.rohit.wikisearchapp.beans.WikiRoomData
import com.rohit.wikisearchapp.utils.AppConstants
import kotlinx.android.synthetic.main.wiki_offline_search_list_fragment.*
import kotlinx.android.synthetic.main.wiki_search_fragment.wikiSearchRv

class WikiOfflineSearchListFragment : Fragment(),WikiSearchDataAdapter.WikiDataSelectionListener {

    private lateinit var viewModel: WikiSearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wiki_offline_search_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = it.run {
                ViewModelProviders.of(this)[WikiSearchViewModel::class.java]
            }
        }

        viewModel.getOfflineData()

        viewModel.wikiRoomOfflineDatas.observe(activity!!, Observer {
            setAdapter(ArrayList(it))
        })

        goOnlineBtn.setOnClickListener {
            findNavController().navigate(R.id.action_wikiOfflineSearchListFragment_to_wikiSearchFragment)
        }

    }

    private fun setAdapter(list: ArrayList<WikiRoomData>?) {
        wikiSearchRv.layoutManager = LinearLayoutManager(activity)
        val adapter = WikiSearchDataAdapter(activity!!,list,this)
        wikiSearchRv.adapter = adapter
    }

    override fun onDataSelected(roomData: WikiRoomData?) {
        roomData?.pageId?.let {
            val arguments = Bundle()
            arguments.putInt(AppConstants.KEY_URL, it)
            findNavController().navigate(R.id.action_wikiOfflineSearchListFragment_to_wikiWebViewFragment, arguments)
        }
    }
}

package com.rohit.wikisearchapp.ui.wikiSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohit.wikisearchapp.R
import com.rohit.wikisearchapp.adapters.WikiSearchDataAdapter
import com.rohit.wikisearchapp.beans.WikiRoomData
import com.rohit.wikisearchapp.utils.AppConstants
import com.rohit.wikisearchapp.utils.WikiSearchTools
import kotlinx.android.synthetic.main.wiki_search_fragment.*

class WikiSearchFragment : Fragment(),WikiSearchDataAdapter.WikiDataSelectionListener {

    private lateinit var viewModel: WikiSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.wiki_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = it.run {
                ViewModelProviders.of(this)[WikiSearchViewModel::class.java]
            }
        }

        searchBtn.setOnClickListener {
            val searchTxt = searchEt.text.toString().trim()
            if (searchTxt.isEmpty()) {
                WikiSearchTools.showToast(getString(R.string.text_no_search_key))
            } else if(WikiSearchTools.checkInternetStatus(activity)){
                searchBtn.isEnabled = false
                searchEt.alpha = 0.2f
                mProgressBar.visibility = View.VISIBLE
                viewModel.search(searchTxt)
            }else{
                WikiSearchTools.showToast(getString(R.string.text_no_internet))
            }
        }

        viewModel.wikiRoomDatas.observe(activity!!, Observer {
            mProgressBar.visibility = View.GONE
            searchBtn.isEnabled = true
            searchEt.alpha = 1f
            setAdapter(it)
        })

        goOfflineBtn.setOnClickListener {
            findNavController().navigate(R.id.action_wikiSearchFragment_to_wikiOfflineSearchListFragment)
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
            findNavController().navigate(R.id.action_wikiSearchFragment_to_wikiWebViewFragment, arguments)
        }
    }

}

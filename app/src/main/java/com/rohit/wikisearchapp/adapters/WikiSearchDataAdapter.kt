package com.rohit.wikisearchapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rohit.wikisearchapp.R
import com.rohit.wikisearchapp.beans.WikiRoomData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_wiki_data.view.*

/**
 * Created by rohit on 5/4/20.
 */

class WikiSearchDataAdapter(val mContext:Context,val wikiRoomDataList: ArrayList<WikiRoomData>?,val wikiDataSelectionListener: WikiDataSelectionListener) : RecyclerView.Adapter<WikiSearchDataAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wiki_data, parent,false)
        return ViewHolder(view,wikiDataSelectionListener)
    }

    override fun getItemCount(): Int {
        return wikiRoomDataList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(wikiRoomDataList?.get(position), mContext)
    }

    class ViewHolder(itemView: View, private val wikiDataSelectionListener: WikiDataSelectionListener) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(wikiRoomData: WikiRoomData?, mContext: Context) {
            itemView.mTitle.text = wikiRoomData?.title
            itemView.mDescription.text = wikiRoomData?.description
            Picasso.with(mContext).load(wikiRoomData?.imageUrl).placeholder(R.drawable.wiki_app_icon).error(R.drawable.wiki_app_icon).into(itemView.searchImage)
            itemView.setOnClickListener {
                wikiDataSelectionListener.onDataSelected(wikiRoomData)
            }
        }
    }

    interface WikiDataSelectionListener{
        fun onDataSelected(roomData: WikiRoomData?)
    }


}
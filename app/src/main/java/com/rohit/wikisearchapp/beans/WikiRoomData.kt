package com.rohit.wikisearchapp.beans

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


/**
 * Created by rohit on 5/3/20.
 */

@Entity
data class WikiRoomData(@PrimaryKey var pageId: Int = 0,
                        var title: String? = null,
                        var description: String? = null,
                        var imageUrl: String? = null,
                        var isUrlCached :Boolean = false,
                        var index:Int=0) : Serializable {



}
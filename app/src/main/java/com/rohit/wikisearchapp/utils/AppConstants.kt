package com.rohit.wikisearchapp.utils

/**
 * Created by rohit on 5/3/20.
 */

object AppConstants {

    const val BASE_URL = "https://en.wikipedia.org//w/api.php/"
    const val BASE_SEARCH_URL = "https://en.wikipedia.org//w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=300&pilimit=10&wbptterms=description&gpssearch=mySearchText&gpslimit=10"

    const val KEY_URL="urlId"
}
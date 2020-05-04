package com.rohit.wikisearchapp.ui.wikiSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rohit.wikisearchapp.R
import com.rohit.wikisearchapp.utils.AppConstants
import kotlinx.android.synthetic.main.wiki_web_view_fragment.*


class WikiWebViewFragment : Fragment() {

    private lateinit var viewModel: WikiSearchViewModel
    private var pageId :Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageId = it.getInt(AppConstants.KEY_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wiki_web_view_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = it.run {
                ViewModelProviders.of(this)[WikiSearchViewModel::class.java]
            }
        }


        val url = "https://en.wikipedia.org/?curid=$pageId"

        mWebView.apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.builtInZoomControls = true

            settings.setAppCacheEnabled(true)
            settings.setAppCachePath(activity?.cacheDir?.path)
            settings.cacheMode = WebSettings.LOAD_DEFAULT

            settings.pluginState = WebSettings.PluginState.ON
            webViewClient = MyWebViewClient()
        }


        mWebView.loadUrl(url)

    }

    inner class MyWebViewClient: WebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {
            hideProgressBarAndShowWebView()
        }
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url);
            return true;
        }
    }
    private fun hideProgressBarAndShowWebView() {
        mProgressLl?.visibility = View.GONE
        mWebView?.visibility = View.VISIBLE
    }


}

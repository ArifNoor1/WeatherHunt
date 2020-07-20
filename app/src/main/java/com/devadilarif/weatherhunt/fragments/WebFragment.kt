package com.devadilarif.weatherhunt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devadilarif.weatherhunt.R
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.news_fragment.*

class WebFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            arguments?.getString("newsLink").let {
                webview.getSettings().setJavaScriptEnabled(true)
                webview.loadUrl(it)
            }


    }



}
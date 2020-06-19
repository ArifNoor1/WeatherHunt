package com.devadilarif.weatherhunt.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.viewmodels.HomeFragmentViewModel
import com.devadilarif.weatherhunt.viewmodels.MyViewModelFactory
import com.devadilarif.weatherhunt.viewmodels.NewsFragmentViewModel


class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsFragmentViewModel
    private lateinit var newsRepository : NewsRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsRepository = NewsRepository(viewLifecycleOwner,context!!)
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!,MyViewModelFactory(NewsFragmentViewModel::class){
            NewsFragmentViewModel(newsRepository)
        }).get(NewsFragmentViewModel::class.java)
    }

}

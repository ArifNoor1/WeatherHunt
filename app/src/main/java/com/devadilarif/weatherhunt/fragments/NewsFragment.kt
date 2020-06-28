package com.devadilarif.weatherhunt.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.HomeFragmentBinding
import com.devadilarif.weatherhunt.databinding.NewsFragmentBinding
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
    lateinit var binding : NewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.news_fragment,container,false)
        newsRepository = NewsRepository(viewLifecycleOwner,context!!)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!,MyViewModelFactory(NewsFragmentViewModel::class){
            NewsFragmentViewModel(newsRepository)
        }).get(NewsFragmentViewModel::class.java)
        binding.newsViewModel = viewModel
    }

}

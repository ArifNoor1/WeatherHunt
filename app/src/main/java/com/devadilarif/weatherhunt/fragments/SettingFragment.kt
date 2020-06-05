package com.devadilarif.weatherhunt.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.viewmodels.SettingFragmentViewModel


class SettingFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingFragment()
    }

    private lateinit var viewModel: SettingFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

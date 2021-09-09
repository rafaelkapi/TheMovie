package com.cactus.themovie.moviedetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.cactus.themovie.R
import com.cactus.themovie.common.base.BaseFragment
import com.cactus.themovie.databinding.FragmentDetailsBinding
import com.cactus.themovie.moviedetails.viewmodel.DetailsViewModel


class DetailsFragment : BaseFragment() {

    private val viewModel by appViewModel<DetailsViewModel>()
    private lateinit var binding: FragmentDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovie()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
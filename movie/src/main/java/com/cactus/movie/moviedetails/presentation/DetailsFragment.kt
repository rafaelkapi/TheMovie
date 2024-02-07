package com.cactus.movie.moviedetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cactus.commons.base.BaseMvvmFragment
import com.cactus.commons.extensions.hide
import com.cactus.commons.livedata.SafeObserver
import com.cactus.commons.livedata.ViewState
import com.cactus.commons.livedata.ViewState.Success
import com.cactus.commons.livedata.ViewState.Loading
import com.cactus.commons.livedata.ViewState.Error
import com.cactus.commons.livedata.ViewState.Normal.position
import com.cactus.commons.viewbinding.viewBinding
import com.cactus.movie.R
import com.cactus.movie.databinding.FragmentDetailsBinding
import com.cactus.movie.moviedetails.presentation.list.DetailsAdapter
import com.cactus.movie.moviedetails.presentation.model.MovieDetailsVo
import com.cactus.movie.moviedetails.presentation.model.SimilarMoviesVo
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class DetailsFragment : BaseMvvmFragment() {

    private val viewModel by appViewModel<DetailsViewModel>()
    private val binding by viewBinding(FragmentDetailsBinding::inflate)

    private val detailsAdapter: DetailsAdapter by lazy { DetailsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
        pullToRefresh()
        setAnimationLike()
        setClickTryAgain()
    }

    private fun setClickTryAgain() {
        binding.error.errorContainer.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, SafeObserver { vo -> handlerState(vo) })

        viewModel.similarMovies.observe(
            viewLifecycleOwner,
            SafeObserver { vo -> populateViewSimilarMovies(vo) })
    }

    @Suppress("UNCHECKED_CAST")
    private fun handlerState(viewState: ViewState) {
        when (viewState) {
            is Success<*> -> {

                (viewState.value as? List<SimilarMoviesVo>)?.let { list ->
                    populateViewSimilarMovies(list)
                }
                (viewState.value as? MovieDetailsVo)?.let {
                    populateViewDetails(it)
                }
                binding.viewSelector.displayedChild = viewState.position
            }

            is Loading -> {
                binding.viewSelector.displayedChild = viewState.position
            }

            is Error -> {
                binding.viewSelector.displayedChild = viewState.position
                showError()
            }

            else -> {
                showError()
            }
        }
    }

    private fun populateViewDetails(vo: MovieDetailsVo) {
        with(binding.content) {
            Picasso.get().load(vo.posterUrl).into(poster)
            title.text = vo.title
            likes.text = vo.likes
            popularity.text = vo.popularity
            overview.text = vo.subtitle
        }
    }

    private fun populateViewSimilarMovies(listVo: List<SimilarMoviesVo>) {
        binding.content.shimmerList.hide()
        detailsAdapter.setViewItems(listVo)
    }

    private fun setAnimationLike() {
        with(binding.content.morphViewLike) { setOnClickListener { morph() } }
    }

    private fun showError() {
        Snackbar.make(
            requireActivity().findViewById(R.id.container),
            getString(R.string.error_conection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setupRecyclerView() {
        binding.content.similarMovies.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = detailsAdapter
        }
    }

    private fun pullToRefresh() {
        binding.content.swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.refresh()
                isRefreshing = false

            }
        }
    }
}



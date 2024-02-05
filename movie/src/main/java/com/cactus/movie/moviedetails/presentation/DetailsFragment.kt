package com.cactus.movie.moviedetails.presentation

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cactus.commons.base.BaseMvvmFragment
import com.cactus.commons.extensions.hide
import com.cactus.commons.extensions.show
import com.cactus.commons.livedata.SafeObserver
import com.cactus.commons.livedata.ViewState
import com.cactus.commons.livedata.ViewState.Success
import com.cactus.commons.livedata.ViewState.Loading
import com.cactus.commons.livedata.ViewState.Error
import com.cactus.commons.viewbinding.viewBinding
import com.cactus.movie.R
import com.cactus.movie.databinding.FragmentDetailsBinding
import com.cactus.movie.moviedetails.presentation.list.DetailsAdapter
import com.cactus.movie.moviedetails.presentation.model.MovieDetailsVo
import com.cactus.movie.moviedetails.presentation.model.SimilarMoviesVo
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class DetailsFragment : BaseMvvmFragment() {

//    private val viewModel by appViewModel<DetailsViewModel>()
    private val binding by viewBinding(FragmentDetailsBinding::inflate)

    private lateinit var containerViewActivityMain: View

    private val detailsAdapter: DetailsAdapter by lazy {
        DetailsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.apply {
//            loadingShow = { progressBar.show() }
//            loadingHide = { progressBar.hide() }
//            loadingSimilarMovieHide = { shimmer_similar_movies.hide() }
//            startAnimationLike = { startAnimationLike() }
//            emptyContainerView = { x -> emptyContainerView(x) }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
        setScrollViewBehavior()
        pullToRefresh()
    }

    private fun setupObservers() {
//        viewModel.state.observe(viewLifecycleOwner, SafeObserver { ::handlerState })
//
//        viewModel.similarMovies.observe(
//            viewLifecycleOwner,
//            SafeObserver { ::populateViewSimilarMovies })
    }

    @Suppress("UNCHECKED_CAST")
    private fun handlerState(viewState: ViewState) {
        when (viewState) {
            is Success<*> -> {

//                (viewState.value as? List<SimilarMoviesVo>)?.let { list ->
//                    populateViewSimilarMovies(list)
//                }
                (viewState.value as? MovieDetailsVo)?.let {
                    populateViewDetails(it)
                }

                binding.progressBar.hide()
            }

            is Loading -> {
                binding.apply {
                    progressBar.show()
                    shimmer.show()
                }
            }

            is Error -> {
                handleEmptyContainer(true)
                showError()
            }

            else -> {
                showError()
            }
        }

    }

    private fun populateViewDetails(vo: MovieDetailsVo) {
        with(binding) {
            Picasso.get().load(vo.posterUrl).into(poster)
            title.text = vo.title
            likes.text = vo.likes
            popularity.text = vo.popularity
            overview.text = vo.subtitle
        }
    }

    private fun populateViewSimilarMovies(listVo: List<SimilarMoviesVo>) {
        binding.shimmer.hide()
        detailsAdapter.setViewItems(listVo)
    }

    private fun startAnimationLike() {
//        binding.morphViewLike.morph()
    }

    private fun setScrollViewBehavior() {
        binding.nestedScrollView.apply {
            this.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, scrollX, scrollY, _, _ ->
                    val boundsAux = Rect()
                    binding.poster.getDrawingRect(boundsAux)
                    val bounds = Rect(
                        boundsAux.left,
                        boundsAux.top,
                        boundsAux.right,
                        ((boundsAux.bottom - (boundsAux.bottom * .05)).toInt())
                    )

                    val scrollBounds = Rect(
                        scrollX,
                        scrollY,
                        (scrollX + this.width),
                        (scrollY + this.height)
                    )

                    if (Rect.intersects(scrollBounds, bounds)) {
                        activity?.apply {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                window.setDecorFitsSystemWindows(false)
                            } else {
                                window.addFlags(
                                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                                )
                            }
                        }

                    } else {
                        activity?.apply {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                window.setDecorFitsSystemWindows(true)
                            } else {
                                window.clearFlags(
                                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                                )
                            }
                        }
                    }
                }
            )
        }
    }

    private fun handleEmptyContainer(show: Boolean) {
        with(binding) {
            if (show) {
                shimmer.hideShimmer()
//                shimmer.hide()
                likes.hide()
                popularity.hide()
                morphViewLike.hide()
            } else {
                shimmer.show()
                likes.show()
                popularity.show()
                morphViewLike.show()
            }
        }

    }

    private fun showError() {
        handleEmptyContainer(true)
        Snackbar.make(
            containerViewActivityMain,
            getString(R.string.error_conection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setupRecyclerView() {
        binding.similarMovies.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = detailsAdapter
        }
    }

    private fun pullToRefresh() {
        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
//                viewModel.getMovie()
                isRefreshing = false

            }
        }
    }

    private fun resetLayout() {
//        viewModel.apply {
//            movieLiveData.value = Movie()
//            genresListLiveData.value?.clear()
//            similarMoviesLiveData.value?.clear()
//            popularityLiveData.value = ""
//            likesLiveData.value = ""
//        }
    }


    override fun onResume() {
        super.onResume()
//        containerViewActivityMain = activity?.findViewById(R.id.navHosFragment)!!
    }
}



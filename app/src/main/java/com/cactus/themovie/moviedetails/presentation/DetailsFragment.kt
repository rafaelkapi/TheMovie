package com.cactus.themovie.moviedetails.presentation

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.cactus.themovie.R
import com.cactus.themovie.common.base.BaseMvvmFragment
import com.cactus.themovie.common.hide
import com.cactus.themovie.common.show
import com.cactus.themovie.databinding.FragmentDetailsBinding
import com.cactus.themovie.moviedetails.presentation.list.DetailsAdapter
import com.cactus.themovie.network.domain.Movie
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : BaseMvvmFragment() {

//    private val viewModel by appViewModel<DetailsViewModel>()
    private lateinit var binding: FragmentDetailsBinding

    private lateinit var containerViewActivityMain: View

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
//        binding.lifecycleOwner = this
//        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getMovie()
        setupObservers()
        setupRecyclerViewCharacters()
        setScrollViewBehavior()
        pullToRefresh()
    }

    private fun setupObservers() {
//        viewModel.similarMoviesLiveData.observe(viewLifecycleOwner, {
//            (binding.rvSimilar.adapter as DetailsAdapter).list = it
//        })
//
//        viewModel.showNotificationErrorLiveData.observe(viewLifecycleOwner,{
//            showErrorNotificationView()
//        })
    }

    private fun startAnimationLike() {
        binding.morphViewLike.morph()
    }

    private fun setScrollViewBehavior() {
        binding.nestedScrollView.apply {
            this.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, scrollX, scrollY, _, _ ->
                    val boundsAux = Rect()
                    binding.ivPoster.getDrawingRect(boundsAux)
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

    private fun emptyContainerView(show: Boolean) {
        if (show) {
            resetLayout()
//            shimmer_similar_movies.hide()
//            tv_likes.hide()
//            iv_popularity.hide()
//            morphView_like.hide()
        } else {
//            shimmer_similar_movies.show()
//            tv_likes.show()
//            iv_popularity.show()
//            morphView_like.show()
        }
    }

    private fun showErrorNotificationView() {
        emptyContainerView(true)
        Snackbar.make(
            containerViewActivityMain,
            getString(R.string.error_conection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setupRecyclerViewCharacters() {
        binding.rvSimilar.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DetailsAdapter()
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
        containerViewActivityMain = activity?.findViewById(R.id.navHosFragment)!!
    }
}



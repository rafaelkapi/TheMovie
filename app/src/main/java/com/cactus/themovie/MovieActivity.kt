package com.cactus.themovie

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import com.cactus.commons.base.BaseMvvmActivity
import com.cactus.themovie.databinding.MovieActivityBinding

class MovieActivity : BaseMvvmActivity() {

     private lateinit var binding: MovieActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MovieActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
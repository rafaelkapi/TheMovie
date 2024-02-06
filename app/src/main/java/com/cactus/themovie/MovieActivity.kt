package com.cactus.themovie

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cactus.commons.base.BaseMvvmActivity
import com.cactus.themovie.databinding.MovieActivityBinding

class MovieActivity : BaseMvvmActivity() {

    private lateinit var binding: MovieActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MovieActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
            )
            window.addFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }


}
package com.cactus.movie.moviedetails.presentation.customView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.cactus.movie.R

class MorphLikeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var secondAnim: AnimatedVectorDrawableCompat? = null
    private var firstAnim: AnimatedVectorDrawableCompat? = null
    private var showingFavorite: Boolean = false

    init {
        showingFavorite = true
        firstAnim = AnimatedVectorDrawableCompat.create(context, R.drawable.avd_nolike_to_like)
        secondAnim = AnimatedVectorDrawableCompat.create(context, R.drawable.avd_like_to_nolike)
        setImageDrawable(firstAnim)
    }

    fun morph() {
        val drawable = if (showingFavorite) firstAnim else secondAnim
        setImageDrawable(drawable)
        drawable?.start()
        showingFavorite = !showingFavorite
    }
}
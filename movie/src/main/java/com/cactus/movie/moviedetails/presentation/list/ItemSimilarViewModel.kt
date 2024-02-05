//package com.cactus.movie.moviedetails.presentation.list
//
//import androidx.databinding.ObservableField
//
//class ItemSimilarViewModel {
//
//    // Observables
//    val title: ObservableField<String> = ObservableField()
//    val posterUrl: ObservableField<String> = ObservableField()
//    val releaseDate: ObservableField<String> = ObservableField()
//    val genres: ObservableField<String> = ObservableField()
//
//     fun setReleaseYear(date: String) {
//        val pattern = "\\d{4}".toRegex()
//        val found = pattern.find(date)
//        releaseDate.set(found?.value)
//    }
//
//}
package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.pages.MovieDetails.MovieDetailsViewModel

// Factory for MovieDetailsViewModelFactory
class MovieDetailsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel() as T
    }
}

package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.pages.GenrePage.GenreViewModel

//Coded by Aidan
// Factory for GenreViewModelFactory
class GenreViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GenreViewModel() as T
    }
}

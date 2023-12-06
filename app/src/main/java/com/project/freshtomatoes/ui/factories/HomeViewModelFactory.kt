package com.project.freshtomatoes.ui.factories

import HomeViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Factory for HomeViewModelFactory
class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }
}

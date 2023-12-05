package com.project.freshtomatoes.ui.components

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.SortState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class ShowReviewsViewModel : ViewModel() {
    private val dateComparator = Comparator {
        mr1: MovieReview, mr2: MovieReview -> if(Date(mr1.review.reviewDate).time < Date(mr2.review.reviewDate).time) 1 else -1
    }
    private val ratingComparator = Comparator {
        mr1: MovieReview, mr2: MovieReview -> if(mr1.review.rating > mr2.review.rating) 1 else -1
    }

    private val _sortState = MutableStateFlow(SortState.None)
    private val _movieReviews = MutableStateFlow<List<MovieReview>>(emptyList())

    val sortState = _sortState.asStateFlow()
    val movieReviews = _movieReviews.asStateFlow()

    fun updateList(newList: List<MovieReview>) {
        if(movieReviews.value.size == newList.size && movieReviews.value.toSet() == newList.toSet()) {
            return
        }

        _movieReviews.update { newList }
    }

    fun setSortState(newState: SortState) {
        _sortState.update { newState }

        updateSorting()
    }

    private fun updateSorting() {
        val reviews = movieReviews.value
        when (sortState.value) {
            SortState.RatingAscending -> {
                _movieReviews.update { reviews.sortedWith(ratingComparator) }
            }
            SortState.RatingDescending -> {
                _movieReviews.update { reviews.sortedWith(ratingComparator.reversed()) }
            }
            SortState.DateAscending -> {
                _movieReviews.update { reviews.sortedWith(dateComparator) }
            }
            SortState.DateDescending -> {
                _movieReviews.update { reviews.sortedWith(dateComparator.reversed()) }
            }
            else -> { } // Do nothing on None.
        }
    }
}

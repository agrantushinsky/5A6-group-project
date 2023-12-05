package com.project.freshtomatoes.ui.components

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.SortState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

/**
 * ShowReviewsViewModel is the viewmodel for the ShowReviews component.
 * Getters & Setters for: sortState, movieReviews.
 * updateList to update when new reviews are being passed
 * updateSorting updates the sorting of the reviews list based on the sort state
 */
class ShowReviewsViewModel : ViewModel() {
    // Sorting comparators
    private val dateComparator = Comparator {
        mr1: MovieReview, mr2: MovieReview -> if(Date(mr1.review.reviewDate).time < Date(mr2.review.reviewDate).time) 1 else -1
    }
    private val ratingComparator = Comparator {
        mr1: MovieReview, mr2: MovieReview -> if(mr1.review.rating > mr2.review.rating) 1 else -1
    }

    // Store original, unsorted list
    private var unsortedList = emptyList<MovieReview>()

    // Getters & setters for StateFlows (sortState, and movieReviews).
    private val _sortState = MutableStateFlow(SortState.None)
    private val _movieReviews = MutableStateFlow<List<MovieReview>>(emptyList())

    val sortState = _sortState.asStateFlow()
    val movieReviews = _movieReviews.asStateFlow()

    // Update the stored list of movies
    fun updateList(newList: List<MovieReview>) {
        // Make sure it's not the same list, by checking size & set.
        if(movieReviews.value.size == newList.size && movieReviews.value.toSet() == newList.toSet()) {
            return
        }

        // Update the list and sorting
        _movieReviews.update { newList }
        unsortedList = newList
        updateSorting()
    }

    // Set the sort state & update sorting
    fun setSortState(newState: SortState) {
        _sortState.update { newState }

        updateSorting()
    }

    // Update the _movieReviews order with the sortState.
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
            else -> {
                // reset to unsorted
                _movieReviews.update { unsortedList }
            }
        }
    }
}

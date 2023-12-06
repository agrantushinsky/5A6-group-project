package com.project.freshtomatoes.ui.components

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.project.freshtomatoes.data.FilterConfig
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.SortState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
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
    private val _filterConfig = MutableStateFlow(FilterConfig())

    val sortState = _sortState.asStateFlow()
    val movieReviews = _movieReviews.asStateFlow()
    val filterConfig = _filterConfig.asStateFlow()

    // Update the stored list of movies
    fun updateList(newList: List<MovieReview>) {
        // Make sure it's not the same list, by checking size & set.
        if(unsortedList.size == newList.size && unsortedList.toSet() == newList.toSet()) {
            return
        }

        // Update the list and sorting
        _movieReviews.update { newList }
        unsortedList = newList

        setDisplayList()
    }

    // Checks if reviews is empty ignoring the filtering
    fun isReviewListEmpty(): Boolean {
        return unsortedList.isEmpty()
    }

    // Set the sort state & update sorting
    fun setSortState(newState: SortState) {
        _sortState.update { newState }

        setDisplayList()
    }

    // Set the filter options
    fun setFilterConfig(newConfig: FilterConfig) {
        _filterConfig.update { newConfig }

        setDisplayList()
    }

    // Sets the display list based on filter config, and sorting state.
    private fun setDisplayList() {
        // Sort, then filter.
        _movieReviews.update { filterList(sortList(unsortedList)) }
    }

    // Get the sorted list based on sorting state
    private fun sortList(reviews: List<MovieReview>): List<MovieReview> {
        when (sortState.value) {
            SortState.RatingAscending -> {
                return reviews.sortedWith(ratingComparator)
            }
            SortState.RatingDescending -> {
                return reviews.sortedWith(ratingComparator.reversed())
            }
            SortState.DateAscending -> {
                return reviews.sortedWith(dateComparator)
            }
            SortState.DateDescending -> {
                return reviews.sortedWith(dateComparator.reversed())
            }
            else -> { // None case:
                return unsortedList
            }
        }
    }

    // Get the filtered list based on filter config
    private fun filterList(reviews: List<MovieReview>): List<MovieReview> {
        var filtered = reviews

        // Min filter
        if(filterConfig.value.minRating != null) {
            filtered = filtered.filter { it.review.rating >= filterConfig.value.minRating!! }
        }

        // Max filter
        if(filterConfig.value.maxRating != null) {
            filtered = filtered.filter { it.review.rating <= filterConfig.value.maxRating!! }
        }

        return filtered
    }
}

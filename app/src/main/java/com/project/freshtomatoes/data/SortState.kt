package com.project.freshtomatoes.data

//Coded by Nitpreet

/**
 * SortState enum for all sorting states for movies.
 * */
enum class SortState {
    None,
    RatingAscending {
        override fun toString(): String {
            return "Rating Ascending"
        }
    },
    RatingDescending {
        override fun toString(): String {
            return "Rating Descending"
        }
    },
    DateAscending {
        override fun toString(): String {
            return "Date Ascending"
        }
    },
    DateDescending {
        override fun toString(): String {
            return "Date Descending"
        }
    }
}

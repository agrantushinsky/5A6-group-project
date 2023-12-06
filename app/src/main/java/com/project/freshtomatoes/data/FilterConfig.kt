package com.project.freshtomatoes.data

/**
 * FilterConfig for filtering user reviews.
 * Contains
 * - minRating: Minimum rating to display
 * - maxRating: Maximum rating to display
 */
data class FilterConfig(
    var minRating: Int? = null,
    var maxRating: Int? = null
)

package com.project.freshtomatoes.data
// Coded by Jose
/**
 * Matches all reviews to their movies using the review's movieId.
 *
 * @param reviews The reviews to match to movies.
 * @param requester TmdbRequest instance.
 * @return List of matched MovieReview(s).
 */
suspend fun MovieReviewMatcher(reviews: List<Review>, requester: TmdbRequest): List<MovieReview> {
    val movieReviews: MutableList<MovieReview> = arrayListOf()
    for (review in reviews) {
        movieReviews.add(MovieReview(requester.details(review.movieId), review))
    }

    return movieReviews
}

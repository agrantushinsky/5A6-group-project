package com.project.freshtomatoes.data

suspend fun MovieReviewMatcher(reviews: List<Review>, requester: TmdbRequest): List<MovieReview> {
    val movieReviews: MutableList<MovieReview> = arrayListOf()
    for (review in reviews) {
        movieReviews.add(MovieReview(requester.details(review.movieId), review))
    }

    return movieReviews
}

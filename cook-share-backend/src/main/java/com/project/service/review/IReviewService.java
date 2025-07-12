package com.project.service.review;

import com.project.request.ReviewRequest;

public interface IReviewService {
    void addReview(Long recipeId, ReviewRequest request);
    void deleteReview(Long recipeId, Long reviewId);
    int getTotalReviews(Long recipeId);
}

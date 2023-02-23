package com.developer.userreview.repository;

import org.springframework.data.repository.CrudRepository;

import com.developer.userreview.entity.UserReview;

public interface UserReviewRepository extends CrudRepository<UserReview, Long> {

}

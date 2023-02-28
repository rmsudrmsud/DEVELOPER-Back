package com.developer.userreview.dto;

import org.hibernate.annotations.DynamicInsert;

import com.developer.appliedlesson.entity.AppliedLesson;

import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicInsert
@Data
@NoArgsConstructor
public class UserReviewDTO {
	private Long applySeqRv;
	private Integer star;
	private String review;
	private AppliedLesson alLesson;
	
	@Data
	@NoArgsConstructor
	public static class addReviewDTO{
		private Integer star;
		private String review;
	}
}

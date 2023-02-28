package com.developer.userreview.dto;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.appliedlesson.entity.AppliedLesson;

import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
public class UserReviewDTO {
	private Long applySeqRv;
	private Integer star;
	private String review;
	private AppliedLesson alLesson;
	
	//근형
	@Data
	@DynamicInsert
	@DynamicUpdate
	@NoArgsConstructor
	public static class addReviewDTO{
		private Long applySeqRv;
		private Integer star;
		private String review;
	}
}

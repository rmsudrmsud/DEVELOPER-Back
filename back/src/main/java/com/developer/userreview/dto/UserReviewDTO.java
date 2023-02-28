package com.developer.userreview.dto;

import com.developer.appliedlesson.dto.AppliedLessonDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

public class UserReviewDTO {

	@Data @NoArgsConstructor
	public static class selectUserReview{
		private Long applySeqRv;
	
		private Integer star;
	
		private String review;
	
		private AppliedLessonDTO appliedLessonDTO;
	}
	
	@Data
	@NoArgsConstructor
	public static class getTuteeReview{
		private Long applySeqRv;
		private Integer star;
		private String review;
		private AppliedLessonDTO.selectAppliedLessonDTO appliedLessonDTO;
	}
	
}

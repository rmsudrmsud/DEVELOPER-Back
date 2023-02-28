package com.developer.appliedlesson.dto;

import java.util.Date;

import com.developer.lessonreview.dto.LessonReviewDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

public class AppliedLessonDTO {

	// [JW]
	@Data
	@NoArgsConstructor
	public static class alDTO {
		private Long applySeq;
		private Date cdate;
		private Integer applyOk;
		private String tuteeId;
	}

	// [JW]
	@Data
	@NoArgsConstructor
	public static class alLessonDTO {
		private String tuteeId;
		private LessonReviewDTO.lrALDTO lrdto;
	}

	// [JW]
	@Data
	@NoArgsConstructor
	public static class alAddRequestDTO {
		private Long applySeq;
		private Date cdate;
		private Integer applyOk;
	}

}

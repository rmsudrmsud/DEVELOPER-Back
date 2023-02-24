package com.developer.lessonreview.dto;

import java.util.Date;

import com.developer.appliedlesson.dto.AppliedLessonDTO;

import lombok.Data;

@Data
public class LessonReviewDTO {
	private Long applySeq;
	private Date cDate;
	private String review;
	private Integer star;	

	private AppliedLessonDTO aldto;
}

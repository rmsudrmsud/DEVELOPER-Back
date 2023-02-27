package com.developer.lessonreview.dto;

import java.util.Date;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

public class LessonReviewDTO {
	
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
	public static class lrDTO{
		private Long applySeq;
		private Date cDate;
		private String review;
		private Integer star;	

		private AppliedLessonDTO.alDTO aldto;
	}
	
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
	public static class lrALDTO{
		private Date cDate;
		private String review;
		private Integer star;	
	}
	
	@Data
	@NoArgsConstructor
	public static class listLRListDTO{
		private String review;
		private Integer star;	
		private String name;
	}
}

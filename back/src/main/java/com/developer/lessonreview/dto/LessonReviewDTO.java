package com.developer.lessonreview.dto;

import java.util.Date;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

public class LessonReviewDTO {
	
	//[JW]
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
	public static class lrDTO{
		private Long applySeq;
		private Date cDate;
		private String review;
		private Integer star;	
	}
	
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
	public static class lrALDTO{
		private Date cDate;
		private String review;
		private Integer star;	
	}
	
	//[JW]
	@Data
	@NoArgsConstructor
	public static class listLRListDTO{
		private String review;
		private Integer star;	
		private String name;
	}
	
	//[JW]
	@Data
	@NoArgsConstructor
	public static class noWriteLReviewDTO{
		private Long applySeq;
		private String lessonName;
	}
	
	//근형
	@Data
	@NoArgsConstructor
	public static class getReviewList{
		private String review;
		private Integer star;
	}
}

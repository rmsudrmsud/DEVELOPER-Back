package com.developer.lesson.dto;



import java.util.Date;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.favoriteslesson.dto.FavoritesLessonDTO;
import com.developer.tutor.dto.TutorDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class LessonDTO {

//	@AllArgsConstructor
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Data
	@NoArgsConstructor
	public static class selectDetailDTO{
		private Long lessonSeq;
		private String lessonName;
		private Integer category;
		private String content;
		private Integer people;
		private String imgPath;
		private Date startCdate;
		private Date endCdate;
		private Integer price;
		private Date startDate;
		private Date endDate;
		private Integer payLesson;
		private String location;

		private TutorDTO tDTO;
		private FavoritesLessonDTO flDTO;
		private AppliedLessonDTO alDTO;
	}
		
		// GetLessonByUser1,2,3
	@Data
	@NoArgsConstructor
	public static class GetLessonByUser {
		private String lessonName;
		private TutorDTO tDTO;	
	}
	
	@Data
	@NoArgsConstructor
	public static class applyLessonBytutee {
		private String lessonName;
		private AppliedLessonDTO.selectAppliedLessonDTO alDTO;
	}
}

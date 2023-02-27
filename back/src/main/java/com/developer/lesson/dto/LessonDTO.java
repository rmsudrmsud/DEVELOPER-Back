package com.developer.lesson.dto;

import java.sql.Date;
import java.util.List;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.favoriteslesson.dto.FavoritesLessonDTO;
import com.developer.tutor.dto.TutorDTO;

import lombok.Data;
import lombok.NoArgsConstructor;


public class LessonDTO {
	
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

	@Data
	@NoArgsConstructor
	public static class onlyLessonDTO{
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
	}

	@Data
	@NoArgsConstructor
	public static class addLessonDTO{
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
	}


	@Data
	@NoArgsConstructor
	public static class selectAllReviewDTO{
		private String lessonName;
		private String name;
		
		private List<AppliedLessonDTO.alLessonDTO> alDTO;
	}
	
	@Data
	@NoArgsConstructor
	public static class flListDTO{
		private String lessonName;
	}
	

}

package com.developer.lesson.dto;


import java.time.LocalDate;
import java.util.List;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.favoriteslesson.dto.FavoritesLessonDTO;
import com.developer.tutor.dto.TutorDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LessonDTO {
	private Long lessonSeq;
	private String lessonName;
	private Integer category;
	private String content;
	private Integer people;
	private String imgPath;
	private LocalDate startCdate;
	private LocalDate endCdate;
	private Integer price;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer payLesson;
	private String location;

	private TutorDTO tDTO;
	private FavoritesLessonDTO flDTO;
	private AppliedLessonDTO alDTO;
	
	//[JW]
	@Data
	@NoArgsConstructor
	public static class selectDetailDTO{
		private Long lessonSeq;
		private String lessonName;
		private Integer category;
		private String content;
		private Integer people;
		private String imgPath;
		private LocalDate startCdate;
		private LocalDate endCdate;
		private Integer price;
		private LocalDate startDate;
		private LocalDate endDate;
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
		private LocalDate startCdate;
		private LocalDate endCdate;
		private Integer price;
		private LocalDate startDate;
		private LocalDate endDate;
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
		private LocalDate startCdate;
		private LocalDate endCdate;
		private Integer price;
		private LocalDate startDate;
		private LocalDate endDate;
		private Integer payLesson;
		private String location;

		private TutorDTO tDTO;
	}

	
	//[JW]
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
	
	//[JW]
	@Data
	@NoArgsConstructor
	public static class allLessonListDTO{
		private String lessonName;
		private Integer category;
		private Integer payLesson;
		private Integer price;
		private String tutorId;
	}
	
	//[JW]
	@Data
	@NoArgsConstructor
	public static class searchLessonDTO{
		private String lessonName;
		private Integer category;
		private String imgPath;
		private LocalDate startCdate;
		private LocalDate endCdate;
		private Integer price;
	}
	
	@Data
	@NoArgsConstructor
	public static class applyLessonDTO{
		private Long lessonSeq;
	}
	

}

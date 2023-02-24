package com.developer.lesson.dto;

import java.sql.Date;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.favoriteslesson.dto.FavoritesLessonDTO;
import com.developer.tutor.dto.TutorDTO;

import lombok.Data;

@Data
public class LessonDTO {
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

package com.developer.lesson.dto;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.favoriteslesson.dto.FavoritesLessonDTO;
import com.developer.lesson.dto.LessonDTO.selectDetailDTO;
import com.developer.tutor.dto.TutorDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@DynamicUpdate
public class LessonDTO {

	//[JH]
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Data
	@NoArgsConstructor
	public static class selectLessonDTO{
		private Long lessonSeq;
		private String lessonName;
		private Integer category;
		private String content;
		private Integer people;
		private String imgPath;
		@Temporal(TemporalType.DATE)
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date startCdate;
		@Temporal(TemporalType.DATE)
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date endCdate;
		private Integer price;
		@Temporal(TemporalType.DATE)
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date startDate;
		@Temporal(TemporalType.DATE)
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date endDate;
		private Integer payLesson;
		private String location;

		private TutorDTO.tutorDTO tDTO;
		private FavoritesLessonDTO flDTO;
		private AppliedLessonDTO alDTO;
	}
	
	
	
	// [JW]
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yy-MM-dd")
	public static class selectDetailDTO {
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

		private TutorDTO.selectDetailDTO tDTO;
		private List<FavoritesLessonDTO.selectDetailDTO> flDTO;
		private List<AppliedLessonDTO.alAddRequestDTO> alDTO;
	}
		
	// [JH]
	@Data
	@NoArgsConstructor
	public static class GetLessonByUser1 {
		private String lessonName;
		private TutorDTO.tutorDTO tDTO;	
	}
	
	// [JH]
	@Data
	@NoArgsConstructor
	public static class GetLessonByUser2 {
		private String lessonName;
		private TutorDTO.tutorDTO tDTO;	
	}
	
	// [JH]
	@Data
	@NoArgsConstructor
	public static class GetLessonByUser3 {
		private String lessonName;
		private TutorDTO.tutorDTO tDTO;	
	}

	// [JH}
	@Data
	@NoArgsConstructor
	public static class applyLessonBytutee {
		private String lessonName;
		private AppliedLessonDTO.selectAppliedLessonDTO alDTO;
	}
	
	// [JH}
	@Data
	@NoArgsConstructor
	public static class notYetLessonBytutee {
		private String lessonName;
		private AppliedLessonDTO.selectAppliedLessonDTO alDTO;
	}
	
	// [JW]
	@Data
	@NoArgsConstructor
	public static class onlyLessonDTO {
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
	public static class addLessonDTO {
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

	// [JW]
	@Data
	@NoArgsConstructor
	public static class selectAllReviewDTO {
		private String lessonName;
		private String name;

		private List<AppliedLessonDTO.alLessonDTO> alDTO;
	}
	
	// [JW]
	@Data
	@NoArgsConstructor
	public static class lessonDetailDTO {
		private selectDetailDTO lessonDto;
		private Integer cnt;
	}
	
	

	@Data
	@NoArgsConstructor
	public static class flListDTO {
		private String lessonName;
	}

	// [JW]
	@Data
	@NoArgsConstructor
	public static class allLessonListDTO {
		private String lessonName;
		private Integer category;
		private Integer payLesson;
		private Integer price;
		private String tutorId;
	}

	// [JW]
	@Data
	@NoArgsConstructor
	public static class searchLessonDTO {
		private String lessonName;
		private Integer category;
		private String imgPath;
		private Date startCdate;
		private Date endCdate;
		private Integer price;
	}

	@Data
	@NoArgsConstructor
	public static class applyLessonDTO {
		private Long lessonSeq;
	}

	// SR
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class selectAllBydateLessonDTO {
		private Long lessonSeq;
		private String lessonName;
		private String imgPath;
		private Integer price;
	}
	//근형
	@Data
    @NoArgsConstructor
    public static class getLessonNameDTO{
   	 private String lessonName;
    }
	//DS
	@Data
	@NoArgsConstructor
	public static class LessonList5DTO{
		private String lessonName;
		private Integer category;
		private Integer people;
	}
	
	
}

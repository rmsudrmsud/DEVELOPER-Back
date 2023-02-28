package com.developer.appliedlesson.dto;

import java.util.Date;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.userreview.dto.UserReviewDTO;
import com.developer.users.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AppliedLessonDTO {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")   
	@Data
	@NoArgsConstructor
	public static class selectAppliedLessonDTO{ 
		private Long applySeq;
		private Date cdate;
		private Integer applyOk;
		private String tuteeId;
	   
	    private LessonDTO.selectDetailDTO lessonDTO;
	    private LessonReviewDTO lessonReviewDTO;
	    private UsersDTO usersDTO;
	    private UserReviewDTO userReviewDTO;
	}

	@Data @NoArgsConstructor
	public static class UserByAppliedLessonDTO{
		private Long applySeq;
		private Integer applyOk;
		private String tuteeId;
		private LessonDTO.selectDetailDTO lessonDTO;
		private UsersDTO usersDTO;
	}
	
	@Data @NoArgsConstructor
	public static class getTutteAppliedLessonDTO{
		private Long applySeq;
		private Date cdate;
		private Integer applyOk;
		private String tuteeId;
	   
	    private LessonDTO.selectDetailDTO lessonDTO;
	    private UsersDTO usersDTO;
	}


}

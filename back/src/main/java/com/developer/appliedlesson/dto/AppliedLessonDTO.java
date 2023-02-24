package com.developer.appliedlesson.dto;

import java.util.Date;

import com.developer.lesson.dto.LessonDTO;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.users.dto.UsersDTO;

import lombok.Data;

@Data
public class AppliedLessonDTO {
	   private Long applySeq;
	   private Date cdate;
	   private Integer applyOk;
	   private String tuteeId;
	   
	   private LessonDTO ldto;
	   private LessonReviewDTO lrdto;
	   private UsersDTO udto;

}

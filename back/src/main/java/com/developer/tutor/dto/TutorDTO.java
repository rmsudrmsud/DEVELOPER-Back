package com.developer.tutor.dto;

import java.util.List;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

public class TutorDTO {
	@Data
	@NoArgsConstructor
	public static class tDTO{
		private String tutorId;
		private String info;
		private String imgPath;
		private Double starAvg;
		private Integer applyOk;
		
		private UsersDTO uDTO;
		private List<LessonDTO> lDTO;
	}
	
	@Data
	@NoArgsConstructor
	public static class saveTutorDTO{
		private String tutorId;
		private String info;
		private String imgPath;
		private Double starAvg;
		private Integer applyOk;
	}
}

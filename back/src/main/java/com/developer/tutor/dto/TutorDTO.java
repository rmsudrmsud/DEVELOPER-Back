package com.developer.tutor.dto;

import java.util.List;

import com.developer.lesson.dto.LessonDTO;
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
		
		private UsersDTO.uNameDTO uDTO;
		private List<LessonDTO> lDTO;
	}
	
	//[JW]
	@Data
	@NoArgsConstructor
	public static class saveTutorDTO{
		private String tutorId;
		private String info;
		private String imgPath;
		private Double starAvg;
		private Integer applyOk;
		
		private Users users;
	}
	
	//[JW]
	@Data
	@NoArgsConstructor
	public static class selectTutorDetailDTO{
		private String info;
		private String imgPath;
		private Double starAvg;
		private String name;
		
		private List<LessonDTO.onlyLessonDTO> lesson;
	}
	
	
}

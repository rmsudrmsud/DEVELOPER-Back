package com.developer.tutor.dto;

import com.developer.lesson.dto.LessonDTO;
import com.developer.users.dto.UsersDTO;

import lombok.Data;

@Data
public class TutorDTO {
	private String tutorId;
	private String info;
	private String imgPath;
	private Double starAvg;
	private Integer applyOk;
	
	private LessonDTO ldto;
	private UsersDTO udto;
}

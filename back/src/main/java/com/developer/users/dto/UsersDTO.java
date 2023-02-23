package com.developer.users.dto;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.tutor.dto.TutorDTO;

import lombok.Data;

@Data
public class UsersDTO {
	private String userId;
	private Integer role;
	private String pwd;
	private String nickname;
	private String name;
	private String email;
	private String tel;
	private String addr;
	
	private TutorDTO tdto;
	private AppliedLessonDTO aldto;
}

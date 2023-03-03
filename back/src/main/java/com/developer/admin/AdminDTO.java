package com.developer.admin;

import java.util.List;

import com.developer.lesson.dto.LessonDTO;
import com.developer.studyroom.dto.StudyroomDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

public class AdminDTO {

	@Data
	@NoArgsConstructor
	public static class getList5DTO{
		private List<StudyroomDTO.StudyroomList5DTO> studyroomList5DTO;
		private List<LessonDTO.LessonList5DTO> lessonList5DTO;
	}
}

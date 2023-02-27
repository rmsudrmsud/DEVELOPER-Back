package com.developer.favoriteslesson.dto;

import com.developer.lesson.dto.LessonDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FavoritesLessonDTO {
	
	@Data
	@AllArgsConstructor @NoArgsConstructor
	public static class flListDTO{
		private Long favLesSeq;
		private String tuteeId;
		
		private String lessonName;
	}
	
	@Data
	@AllArgsConstructor @NoArgsConstructor
	public static class favoritesLessonDTO{
		private Long favLesSeq;
		private String tuteeId;
		
		private LessonDTO.onlyLessonDTO ldto;
	}

}

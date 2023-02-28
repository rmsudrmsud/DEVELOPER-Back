package com.developer.favoriteslesson.dto;

import com.developer.lesson.dto.LessonDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FavoritesLessonDTO {
	
	 //[JW]
	@Data
	@AllArgsConstructor @NoArgsConstructor
	public static class flListDTO{
		private Long favLesSeq;
		private String tuteeId;
		
		private String lessonName;
	}

	 //[JW]
	@Data
	@AllArgsConstructor @NoArgsConstructor
	public static class favoritesLessonDTO{
		private Long favLesSeq;
		private String tuteeId;
		
		private LessonDTO.onlyLessonDTO ldto;
	}

}

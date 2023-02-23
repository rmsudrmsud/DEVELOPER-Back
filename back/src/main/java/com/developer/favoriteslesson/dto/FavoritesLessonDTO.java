package com.developer.favoriteslesson.dto;

import com.developer.lesson.dto.LessonDTO;

import lombok.Data;

@Data
public class FavoritesLessonDTO {
		
	private Long favLesSeq;
	private String tuteeId;
	
	private LessonDTO ldto;
}

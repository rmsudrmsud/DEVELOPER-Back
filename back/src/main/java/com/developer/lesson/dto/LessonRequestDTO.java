package com.developer.lesson.dto;

import java.util.Date;

import com.developer.board.dto.BoardDTO;
import com.developer.users.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LessonRequestDTO {
	private Long lessonSeq;
	private String lessonName;
	private Integer category;
	private String content;
	private Integer people;
	private String imgPath;
	private Date startCdate;
	private Date endCdate;
	private Integer price;
	private Date startDate;
	private Date endDate;
	private Integer payLesson;
	private String location;

	// SR
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class selectAllBydateLessonDTO {
		private Long lessonSeq;
		private String lessonName;
		private String imgPath;
		private Integer price;
	}
}

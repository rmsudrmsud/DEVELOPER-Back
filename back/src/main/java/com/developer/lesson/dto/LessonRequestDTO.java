package com.developer.lesson.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
}

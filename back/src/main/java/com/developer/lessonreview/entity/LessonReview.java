package com.developer.lessonreview.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.developer.appliedLesson.entity.AppliedLesson;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="LESSON_REVIEW")

@Setter@Getter
@NoArgsConstructor @AllArgsConstructor
@JsonFormat(pattern = "yy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
@SequenceGenerator(
		name ="leRevSeq", // 사용할 sequence 이름
		sequenceName ="le_rev_seq", // 실제 데이터베이스 sequence 이름
		initialValue = 1, allocationSize = 1 
		)
public class LessonReview {
	@Id
	@Column(name="le_rev_seq")
	@GeneratedValue( 
			strategy = GenerationType.SEQUENCE, 
			generator ="leRevSeq" // 위의 sequence 이름 
		)
	private Long leRevSeq;
	@Column(name="cdate")
	private Date cDate;
	@Column(name="review")
	private String review;
	@Column(name="star")
	private Integer star;	
	
	@ManyToOne
	@JoinColumn(name="lr_applySeq")
	private AppliedLesson alLesson;
}

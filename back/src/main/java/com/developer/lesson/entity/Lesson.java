package com.developer.lesson.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.favoriteslesson.entity.FavoritesLesson;
import com.developer.tutor.entity.Tutor;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="LESSON")
@DynamicInsert

@Setter@Getter
@NoArgsConstructor @AllArgsConstructor
@JsonFormat(timezone = "Asia/Seoul", pattern ="yy-MM-dd")
@SequenceGenerator(
		name ="lessonSeq", // 사용할 sequence 이름
		sequenceName ="lesson_seq", // 실제 데이터베이스 sequence 이름
		initialValue = 1, allocationSize = 1 
		)
public class Lesson {
	@Id
	@Column(name="lesson_seq")
	@GeneratedValue( 
			strategy = GenerationType.SEQUENCE, 
			generator ="lessonSeq" // 위의 sequence 이름 
		)
	private Long lessonSeq;

	@Column(name="lesson_name")
	private String lessonName;
	@Column(name="category")
	private Integer category;
	@Column(name="content")
	private String content;
	@Column(name="people")
	private Integer people;
	@Column(name="img_path")
	private String imgPath;
	@Column(name="start_cdate")
	private Date startCdate;
	@Column(name="end_cdate")
	private Date endCdate;
	@Column(name="price")
	private Integer price;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	@Column(name="pay_lesson")
	private Integer payLesson;
	@Column(name="location")
	private String location;
	

	@OneToMany(mappedBy = "lesson", 
							fetch = FetchType.EAGER)
	private List<FavoritesLesson> flList;
	
	@OneToMany(mappedBy = "lesson")	
	private List<AppliedLesson> alList;
	
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private Tutor tutor;
	
}

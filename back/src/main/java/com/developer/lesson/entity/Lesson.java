package com.developer.lesson.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
		name ="lessonSeq", 
		sequenceName ="lesson_seq", 
		initialValue = 1, allocationSize = 1 
		)
public class Lesson {
	@Id
	@Column(name="lesson_seq")
	@GeneratedValue( 
			strategy = GenerationType.SEQUENCE, 
			generator ="lessonSeq"  
		)
	private Long lessonSeq;

	@Column(name="lesson_name", nullable = false)
	private String lessonName;
	@Column(name="category", nullable = false)
	private Integer category;
	@Column(name="content", nullable = false)
	private String content;
	@Column(name="people", nullable = false)
	private Integer people;
	@Column(name="img_path")
	private String imgPath;
	@Column(name="start_cdate", nullable = false)
	private Date startCdate;
	@Column(name="end_cdate", nullable = false)
	private Date endCdate;
	@Column(name="price", nullable = false)
	private Integer price;
	@Column(name="start_date", nullable = false)
	private Date startDate;
	@Column(name="end_date", nullable = false)
	private Date endDate;
	@Column(name="pay_lesson", columnDefinition = "NUMBER DEFAULT 0")
	private Integer payLesson;
	@Column(name="location" , nullable = false)
	private String location;
	
	@ManyToOne
	@JoinColumn(name="tutor_id")
	private Tutor tutor;

	@OneToMany(mappedBy = "lesson", 
							cascade = CascadeType.REMOVE)
	private List<FavoritesLesson> flList;
	
	@OneToMany(mappedBy = "lesson")	
	private List<AppliedLesson> alList;
	
}

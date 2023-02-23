package com.developer.appliedlesson.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.lesson.entity.Lesson;
import com.developer.lessonreview.entity.LessonReview;
import com.developer.userreview.entity.UserReview;
import com.developer.users.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="APPLIED_LESSON")
@DynamicInsert
@DynamicUpdate
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@JsonFormat(pattern = "yy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
@SequenceGenerator(
		name ="applySeq", 
		sequenceName ="apply_seq", 
		initialValue = 1, allocationSize = 1 
		)
public class AppliedLesson {
	@Id
	@Column(name="apply_seq")
	@GeneratedValue( 
			strategy = GenerationType.SEQUENCE, 
			generator ="applySeq"  
		)
	private Long applySeq;
	@ColumnDefault(value="SYSDATE")
	@Column
	private Date cdate;
	@ColumnDefault(value="0")
	@Column(name="apply_ok")
	private Integer applyOk;
	

	@Column(name="user_id", nullable = false)
	private String userId;

	@ManyToOne
	@JoinColumn(name="al_lessonSeq")
	private Lesson lesson;
	
	@OneToMany(mappedBy = "alLesson")
	private List<LessonReview> lrList;
	
	@JoinColumn(name = "al_user_id")
	@ManyToOne
	private Users users;
	
	@OneToOne(mappedBy = "alLesson")
	private UserReview userReview;
	
	
	
}

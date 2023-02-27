package com.developer.appliedlesson.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="APPLIED_LESSON")
@DynamicInsert @DynamicUpdate

@Getter @NoArgsConstructor 
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
	@Column(name = "cdate")
	private Date cdate;
	@ColumnDefault(value="0")
	@Column(name="apply_ok")
	private Integer applyOk;
	
	@ManyToOne
	@JoinColumn(name="al_lesson_seq")
	private Lesson lesson;
	
	 @OneToOne(mappedBy = "appliedLesson",
  				cascade = CascadeType.MERGE)	
	 private LessonReview lessonReview;
	
	@JoinColumn(name = "al_tutee_id")
	@ManyToOne
	private Users users;
	
	@OneToOne(mappedBy = "alLesson")
	private UserReview userReview;
   
}
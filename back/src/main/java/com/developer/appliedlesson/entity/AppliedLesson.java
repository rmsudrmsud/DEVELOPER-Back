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
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.users.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @DynamicInsert
@DynamicUpdate
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "applied_lesson")
public class AppliedLesson {
	
	@Id
	@Column(name = "apply_seq")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =	"apply_seq"
			)
	private Long applySeq;
	
	@ManyToOne
	@JoinColumn(name = "lesson_seq", nullable = false)
	private Long lessonSeq;
	
	@JoinColumn(name = "user_id")
	private String userId;
	
	@JsonFormat(pattern = "yy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
	@Column(name = "c_date")
	private Date cdate;
	
	@Column(name = "apply_ok")
	private Long applyOk;
	
	@OneToMany
	private List<Users> usersVO;

//	@ManyToOne
//	private LessonVO lessonVO;
	
//	@OneToMany
//	private List<UserReviewVO> userReviewVO;
	
//	@OneToOne
//	private LessonReviewVO lessonReviewVO;
	
}

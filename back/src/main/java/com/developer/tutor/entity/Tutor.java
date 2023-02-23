package com.developer.tutor.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.users.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TUTOR")
@DynamicInsert
@DynamicUpdate
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Tutor {
	@Id
	@Column(name="tutor_id")
	private String tutorId;
	@Column(name="info")
	private String info;
	@Column(name="img_path")
	private String imgPath;
	@Column(name="star_avg")
	private Integer starAvg;
	@Column(name="apply_ok")
	private Integer applyOk;
	
	@MapsId(value="tutorId")
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "tutor_id")
	private Users users;
	
	
//	@OneToMany(mappedBy = "tutor")
//	private List<Lesson> lesson;
//	@OneToMany(mappedBy = "tutor")
//	private List<AppliedLesson> alLesson;
	
	//private UsersVO usersVO;
}

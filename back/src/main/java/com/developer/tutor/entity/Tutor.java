package com.developer.tutor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="TUTOR")
@DynamicInsert

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Tutor {
	@Id
	@Column(name="user_id")
	private String userId;
	@Column(name="info")
	private String info;
	@Column(name="img_path")
	private String imgPath;
	@Column(name="star_avg")
	private Integer starAvg;
	@Column(name="apply_ok")
	private Integer applyOk;
	
	//private UsersVO usersVO;
}

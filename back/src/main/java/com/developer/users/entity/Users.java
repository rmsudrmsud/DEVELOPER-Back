package com.developer.users.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.tutor.entity.Tutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="users")
@DynamicInsert
@DynamicUpdate
public class Users{
	
	@Id
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@ColumnDefault(value="2")
	private Integer role;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false)
	private String nickname;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String tel;
	
	@Column
	private String addr;
	
	@OneToMany(mappedBy = "users")
	private List<AppliedLesson> alLesson;
	
	@OneToOne(mappedBy = "users")
	private Tutor tutor;
	
	}



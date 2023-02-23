package com.developer.users.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.tutor.entity.Tutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@DynamicInsert
@DynamicUpdate

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Users{	
	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name="role", columnDefinition = "NUMBER DEFAULT 2")
	private Integer role;
	@Column(name="pwd", nullable = false)
	private String pwd;
	@Column(name="nickname", nullable = false)
	private String nickname;
	@Column(name="name", nullable = false)
	private String name;
	@Column(name="email", nullable = false)
	private String email;
	@Column(name="tel", nullable = false)
	private String tel;
	@Column(name="addr", nullable = false)
	private String addr;
	
	
	@OneToOne(mappedBy = "users",
			cascade = CascadeType.REMOVE)
	private Tutor tutor;
	
	@OneToMany(mappedBy = "user")
	private List<AppliedLesson> appliedLesson;
	}

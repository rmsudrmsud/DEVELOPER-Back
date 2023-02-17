package com.developer.users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="users")
@DynamicInsert
@DynamicUpdate
public class Users{
	
	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column
	private int role;
	
	@Column
	private String pwd;
	
	@Column
	private String nickname;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String tel;
	
	@Column
	private String addr;
	}

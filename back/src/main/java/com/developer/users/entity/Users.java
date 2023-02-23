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
package com.developer.users.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.board.entity.Board;
import com.developer.boardrep.entity.BoardRep;
import com.developer.recommend.entity.Recommend;

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
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "role")
	@ColumnDefault(value="0")
	private int role;
	
	@Column(name = "pwd")
	private String pwd;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "addr")
	private String addr;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="users")
	private List<Board> board;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="users")
	private List<BoardRep> boardRep;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="users")
	private List<Recommend> recommend;
	}


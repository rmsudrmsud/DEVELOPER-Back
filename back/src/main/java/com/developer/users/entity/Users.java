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


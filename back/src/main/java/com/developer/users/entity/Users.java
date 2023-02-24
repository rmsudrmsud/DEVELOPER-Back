package com.developer.users.entity;

import java.util.List;

import javax.persistence.CascadeType;
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
import com.developer.board.entity.Board;
import com.developer.boardrep.entity.BoardRep;
import com.developer.favoritesstudyroom.entity.FavoritesStudyroom;
import com.developer.recommend.entity.Recommend;
import com.developer.reservation.entity.Reservation;
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

	@Column(name="tel", nullable = false)
	private String tel;
	@Column(name="addr", nullable = false)
	private String addr;	
	
	@OneToOne(mappedBy = "users",
			cascade = CascadeType.REMOVE)
	private Tutor tutor;
	
	@OneToMany(mappedBy = "users")
	private List<AppliedLesson> appliedLesson;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="users")
	private List<Board> board;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="users")
	private List<BoardRep> boardRep;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="users")
	private List<Recommend> recommend;
	
   @OneToMany(cascade = CascadeType.REMOVE, mappedBy="userId")
   private List<Reservation> reservation;
   
   @OneToMany(cascade=CascadeType.REMOVE, mappedBy = "userId")
   private List<FavoritesStudyroom> favStudyroom;
   

	
	}


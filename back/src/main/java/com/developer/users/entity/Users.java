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
import com.developer.favaritesstudyroom.entity.FavoritesStudyroom;
import com.developer.recommend.entity.Recommend;
import com.developer.reservation.entity.Reservation;
import com.developer.tutor.entity.Tutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class Users {

	@Id
	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "role", columnDefinition = "NUMBER DEFAULT 2")
	@ColumnDefault(value = "0")
	private Integer role;

	@Column(name = "pwd", nullable = false)
	private String pwd;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "tel")
	private String tel;

	@Column(name = "addr", nullable = false)
	private String addr;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "users")
	private List<Board> board;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "users")
	private List<BoardRep> boardRep;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "users")
	private List<Recommend> recommend;

	@OneToOne(mappedBy = "users", cascade = CascadeType.REMOVE)
	private Tutor tutor;

	@OneToMany(mappedBy = "users")
	private List<AppliedLesson> alLesson;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "userId")
	private List<Reservation> reservation;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "userId")
	private List<FavoritesStudyroom> favStudyroom;
}
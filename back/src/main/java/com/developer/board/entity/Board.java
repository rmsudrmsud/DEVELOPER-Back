package com.developer.board.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.boardrep.entity.BoardRep;
import com.developer.recommend.entity.Recommend;
import com.developer.users.entity.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter	@NoArgsConstructor

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="Board")
@SequenceGenerator(
name = "POST_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"post_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)


public class Board {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ_GENERATOR" // 위의 sequence 이름
			)
	private Long postSeq;
	
	@Column(name="category")
	@ColumnDefault(value="0")
	private Integer category; //0:Q/A,  1:스터디모집,  2:자유 게시판
	
	@NotNull
	@Column(name="title")
	private String title;
	
	@NotNull
	@Column(name="content")
	private String content;
	
	@Column(name="img_path")
	private String imgPath;
	
	@Column(name="c_date")
	@ColumnDefault(value="SYSDATE")
	private Date cDate;
	
	@Column(name="recommend")
	@ColumnDefault(value="0")
	private Integer recommend;
	
	@Column(name="cnt")
	@ColumnDefault(value="0")
	private Integer cnt;
	
	
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE},  mappedBy="board")
	private List<BoardRep> boardRep;
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, mappedBy="board")
	private List<Recommend> Recommend;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private Users users;
}

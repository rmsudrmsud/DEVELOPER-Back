package com.developer.board.entity;



import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import com.developer.boardrep.entity.BoardRep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter	@NoArgsConstructor
@AllArgsConstructor

@Entity
@DynamicInsert
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
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="category")
	private Integer category; //0:Q/A,  1:스터디모집,  2:자유 게시판
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="img_path")
	private String imgPath;
	
	@CreatedDate
	@Column(name="c_date")
	//private Date cDate;
	private Date cDate;
	
	@Column(name="recommend")
	private Integer recommend=0;
	
	@Column(name="cnt")
	private Integer cnt=0;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="post_seq")
	private List<BoardRep> boardRep;
	
	//	private UsersVO usersVO;
	//	private List<RecommendVO> recommendVO;
}

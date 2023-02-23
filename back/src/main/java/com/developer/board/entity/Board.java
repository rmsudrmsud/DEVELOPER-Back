package com.developer.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter	@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Board")
@SequenceGenerator(name = "post_seq_generator", // 사용할 sequence 이름
sequenceName = "post_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)

public class Board {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq_generator" // 위의 sequence 이름
	)
	
	@Id
	@Column(name="post_seq")
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
	
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
	@Column(name="c_date")
	private Date cDate;
	
	@Column(name="recommend")
	private Integer recommend;
	
	@Column(name="cnt")
	private Integer cnt;
	
	//	private UsersVO usersVO;
	//	private List<RecommendVO> recommendVO;
	//	private List<BoardRepVO> boardRepVO;
}

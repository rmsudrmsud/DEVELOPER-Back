package com.developer.recommend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter	@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="RECOMMEND")

@SequenceGenerator(name = "rec_seq_generator", // 사용할 sequence 이름
sequenceName = "rec_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)

public class Recommend {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rec_seq_generator" // 위의 sequence 이름
			)
	@Id
	@Column(name="rec_seq")
	private Long recSeq;
	
	@Column(name="post_seq")
	private Integer postSeq;
	
	@Column(name="user_Id")
	private String userId;
}
package com.developer.recommend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.developer.board.entity.Board;
import com.developer.users.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter	@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
name = "REC_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"rec_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)

@Entity
@Table(name="RECOMMEND")
public class Recommend {
	@Id
	@Column(name="rec_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REC_SEQ_GENERATOR" // 위의 sequence 이름
			)
	private Long recSeq;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_seq", nullable=false)
	private Board board;
	
//	@Column(name="post_seq")
//	private Integer postSeq;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable=false)
	private Users users;
	
//	@Column(name="user_Id")
//	private String userId;
}

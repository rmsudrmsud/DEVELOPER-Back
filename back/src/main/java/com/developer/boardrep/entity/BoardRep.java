package com.developer.boardrep.entity;



import java.util.Date;

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


@Entity
@Table(name = "board_rep")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@SequenceGenerator(
name = "POST_REP_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"post_rep_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)

public class BoardRep {
	@Id
	@Column(name = "post_req_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_REP_SEQ_GENERATOR" // 위의 sequence 이름
			)
	private Long postRepSeq;

	@Column(name = "content")
	private String content;

	@Column(name = "cdate")
	private Date cDate;

	@Column(name = "post_seq")
	private Integer postSeq;

	@Column(name = "user_id")
	private String userId;
}

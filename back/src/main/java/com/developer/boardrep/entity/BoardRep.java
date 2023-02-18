package com.developer.boardrep.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "board_rep")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BoardRep {
	@Id
	@Column(name = "post_req_seq")
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

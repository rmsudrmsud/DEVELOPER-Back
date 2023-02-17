package com.developer.recommend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RECOMMEND")
public class Recommend {
	@Id
	@Column(name="rec_seq")
	private Long recSeq;
	
	@Column(name="post_seq")
	private Integer postSeq;
	
	@Column(name="user_Id")
	private String userId;
}

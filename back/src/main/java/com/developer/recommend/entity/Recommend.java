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

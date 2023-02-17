package com.developer.userreview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "user_review")
public class UserReview {
	
	@Id
	@Column(name = "apply_seq")
	@GeneratedValue(
	strategy = GenerationType.SEQUENCE,
	generator =
	"apply_seq" // 위의 sequence 이름
	)
	private int applySeq;
	
	@Column
	private int star;
	
	@Column
	private String review;
	
}

package com.developer.userreview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "user_review")
public class UserReview {
	
	@Id
	@Column(name = "apply_seq")
	
	private int applySeq;
	
	@Column
	private int star;
	
	@Column
	private String review;
	
}

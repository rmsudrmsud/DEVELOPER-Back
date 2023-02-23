package com.developer.boardrep.entity;

import java.sql.Date;

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
<<<<<<< HEAD
=======

>>>>>>> b6d5ba25232e35028a380fd6d19c05e081868f4c
@Entity
@Table(name = "board_rep")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@SequenceGenerator(name = "post_rep_seq_generator", // 사용할 sequence 이름
sequenceName = "post_rep_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)

public class BoardRep {
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_rep_seq_generator" // 위의 sequence 이름
			)
	
	@Id
	@Column(name = "post_req_seq")
	private Long postRepSeq;
=======
   @Id
   @Column(name = "post_req_seq")
   private Long postRepSeq;
>>>>>>> b6d5ba25232e35028a380fd6d19c05e081868f4c

   @Column(name = "content")
   private String content;

   @Column(name = "cdate")
   private Date cDate;

   @Column(name = "post_seq")
   private Integer postSeq;

   @Column(name = "user_id")
   private String userId;
}
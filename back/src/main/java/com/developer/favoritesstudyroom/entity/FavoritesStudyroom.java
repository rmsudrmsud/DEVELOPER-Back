
package com.developer.favoritesstudyroom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.developer.studyroom.entity.Studyroom;
import com.developer.users.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Favorites_Studyroom")
@SequenceGenerator(
name =
"FAV_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"fav_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)
public class FavoritesStudyroom {
	@Id
	@Column(name="fav_seq",nullable = false)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =
			"FAV_SEQ_GENERATOR") 
	private Long favSeq;
	
//	@Column(name="sr_seq")
//	private Long srSeq;
//	
	
//	@Column(name="user_Id")
//	private String userId;

	@ManyToOne
	@JoinColumn(name="user_id")
	private Users userId;
	

	//양방향
	@ManyToOne
	@JoinColumn(name = "sr_seq")
	private Studyroom studyroomFav;	
	
	//private Integer cnt;
	
}
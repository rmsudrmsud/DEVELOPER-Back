package com.developer.favoritestudyroom.entity;

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
	@Column(name="fav_seq")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =
			"FAV_SEQ_GENERATOR") 
	private Long favSeq;
	
	@Column(name="sr_seq")
	private Long srSeq;
	
	@Column(name="user_Id")
	private String userId;

	@Column(name="cnt")
	private Integer cnt;
	
	//private StudyroomVO studyroomVO; 
}
========
package com.developer.favaritesstudyroom.entity;

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
	@Column(name="fav_seq")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =
			"FAV_SEQ_GENERATOR") 
	private Long favSeq;
	
	@Column(name="sr_seq")
	private Long srSeq;
	
	@Column(name="user_Id")
	private String userId;

	@Column(name="cnt")
	private Integer cnt;
	
	//private StudyroomVO studyroomVO; 
}
>>>>>>>> 27530127c719d1d04269250f27e3d6eb6704271f:back/src/main/java/com/developer/favaritesstudyroom/entity/FavoritesStudyroom.java

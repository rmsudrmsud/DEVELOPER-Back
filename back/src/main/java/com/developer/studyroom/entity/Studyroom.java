package com.developer.studyroom.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.favoritesstudyroom.entity.FavoritesStudyroom;
import com.developer.reservation.entity.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor 
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "studyroom")
@SequenceGenerator(
name =
"SR_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"sr_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)
public class Studyroom {
	
	
		@Id
		@Column(name="sr_seq")
		@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator =
				"SR_SEQ_GENERATOR") 
		private long srSeq;
		
		@Column(name="name")
		private String name;
		
		@Column(name="addr")
		private String addr;
		
		@Column(name="info")
		private String info;
		
		@Column(name="open_time")
		private String openTime;
		
		@Column(name="end_time")
		private String endTime;
		
		@Column(name="img_path")
		private String imgPath;
		
		@Column(name="oc")
		private Integer oc;
		
		@Column(name="host_id")
		private String hostId;
		
		//private List<RoomInfoVO> roomInfoVO;
		
		//private HostUserVO hostUserVO;
		
		@OneToMany(mappedBy = "studyroomFav", cascade = CascadeType.REMOVE)
		private List<FavoritesStudyroom> favoritesStudyroom;
		
//		@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//		@JoinColumn(name="res_seq")
//		private Reservation reservation;
		
		
		
		
//		//sr: 카페등록때 필요한 생성자  DTO로 나중에 만들기
//		public StudyroomVO(int srSeq, String name, String addr, String info, String openTime, String endTime,
//				String imgPath, int oc, String hostId) {
//			super();
//			this.srSeq = srSeq;
//			this.name = name;
//			this.addr = addr;
//			this.info = info;
//			this.openTime = openTime;
//			this.endTime = endTime;
//			this.imgPath = imgPath;
//			this.oc = oc;
//			this.hostId = hostId;
//		}
		
}
package com.developer.studyroom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor 

@Entity
@Table(name = "studyroom")
public class Studyroom {
	
	
		@Id
		@Column(name="sr_seq")
		private long srSeq;
		
		@Column(name="name")
		private String name;
		
		@Column(name="addr")
		private String addr;
		
		@Column(name="info")
		private String info;
		
		@Column(name="open_time")
		private String openTime;
		
		@Column(name="end_time")
		private String endTime;
		
		@Column(name="img_path")
		private String imgPath;
		
		@Column(name="oc")
		private Integer oc;
		
		@Column(name="host_id")
		private String hostId;
		
		//private List<RoomInfoVO> roomInfoVO;
		
		//private HostUserVO hostUserVO;
		
		//private FavoritesStudyroomVO favoritesStudyroomVO;
		
//		//sr: 카페등록때 필요한 생성자  DTO로 나중에 만들기
//		public StudyroomVO(int srSeq, String name, String addr, String info, String openTime, String endTime,
//				String imgPath, int oc, String hostId) {
//			super();
//			this.srSeq = srSeq;
//			this.name = name;
//			this.addr = addr;
//			this.info = info;
//			this.openTime = openTime;
//			this.endTime = endTime;
//			this.imgPath = imgPath;
//			this.oc = oc;
//			this.hostId = hostId;
//		}
		
}

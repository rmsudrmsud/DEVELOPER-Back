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

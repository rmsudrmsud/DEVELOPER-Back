package com.developer.reservation.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "RESERVATION")
@SequenceGenerator(
name =
"RES_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"res_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)
public class Reservation {
	@Id
	@Column(name = "res_seq")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =
			"RES_SEQ_GENERATOR") 
	private Long resSeq;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "room_seq")
	private Long roomSeq;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "using_date")
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
	private Date usingDate;
	
	@Column(name = "host_id")
	private String hostId;

	//private StudyroomVO studyroomVO; // sr추가: 해당 스터디카페 예약자명단 전체목록
	//private UsersVO usersVO; // sr추가: 해당 스터디카페 예약자명단 전체목록
	//private HostUserVO hostUserVO; // sr추가: 해당 스터디카페 예약자명단 전체목록
	//private List<RoomReviewVO> roomReviewVO; //ds 추가
}
package com.developer.reservation.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "RESERVATION")
@SequenceGenerator(
name =
"RES_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"res_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)
public class Reservation {
	@Id
	@Column(name = "res_seq")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =
			"RES_SEQ_GENERATOR") 
	private Long resSeq;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "room_seq")
	private Long roomSeq;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "using_date")
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
	private Date usingDate;
	
	@Column(name = "host_id")
	private String hostId;

	//private StudyroomVO studyroomVO; // sr추가: 해당 스터디카페 예약자명단 전체목록
	//private UsersVO usersVO; // sr추가: 해당 스터디카페 예약자명단 전체목록
	//private HostUserVO hostUserVO; // sr추가: 해당 스터디카페 예약자명단 전체목록
	//private List<RoomReviewVO> roomReviewVO; //ds 추가
}

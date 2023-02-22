package com.developer.reservation.entity;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.ibatis.annotations.One;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.hostuser.entity.HostUser;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roomreview.entity.RoomReview;
import com.developer.studyroom.entity.Studyroom;
import com.developer.users.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "RESERVATION")
@DynamicInsert
@DynamicUpdate
@JsonFormat(pattern = "yy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
@SequenceGenerator(
name =
"RES_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"res_seq", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1)
public class Reservation {
	@Id
	@Column(name = "res_seq", nullable = false)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =
			"RES_SEQ_GENERATOR") 
	private Long resSeq;

	
//	@Column(name = "room_seq")
//	private Integer roomSeq;
	
	@Column(name = "start_time", nullable = false)
	private String startTime;
	
	@Column(name = "end_time", nullable = false)
	private String endTime;
	
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
	@ColumnDefault(value="sysdate")
	@Column(name = "using_date")
	private Date usingDate;
	
//	@ManyToOne
//	@JoinColumn(name = "host_id")
//	private String hostId;

	// 단방향, sr추가: 해당 스터디카페 예약자명단 전체목록
//	@OneToMany( mappedBy = "reservation")
//	private List<Studyroom> studyroom;
	
	@ManyToOne
	@JoinColumn(name="sr_seq")
	private Studyroom studyroom;
	
	
	//단방향, sr추가: 해당 스터디카페 예약자명단 전체목록
//	@OneToMany(	fetch = FetchType.EAGER, mappedBy = "reservation")
//	private List<Users> user;
//	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users userId;
	
	//단방향, sr추가: 해당 스터디카페 예약자명단 전체목록
	@ManyToOne
	@JoinColumn(name="host_Id")
	private HostUser hostUser;
	
	@ManyToOne
	@JoinColumn(name="room_seq")
	private RoomInfo roomInfo;
	
	
	@OneToOne(mappedBy = "reservation",fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private RoomReview RoomReviewResSeq;
	
	
//	@OneToMany(mappedBy = "reservationRoomReview", 
//			cascade = CascadeType.REMOVE)
//	private List<RoomReview> roomReview;
	
	

}
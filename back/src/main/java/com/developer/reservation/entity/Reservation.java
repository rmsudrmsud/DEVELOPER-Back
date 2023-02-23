package com.developer.reservation.entity;

import java.sql.Date;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.hostuser.entity.HostUser;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roomreview.entity.RoomReview;
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

	@ManyToOne
	@JoinColumn(name="user_id")
	private Users userId;
	
	@ManyToOne//(cascade= {CascadeType.MERGE})
	@JoinColumn(name ="host_id", nullable = false)
	private HostUser hostUser; 
	
	@ManyToOne//(cascade= {CascadeType.MERGE})
	@JoinColumn(name ="room_seq", nullable = false)
	private RoomInfo roominfo;
	
	@Column(name = "start_time", nullable = false)
	private String startTime;
	
	@Column(name = "end_time", nullable = false)
	private String endTime;
	
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
	@Column(name = "using_date")
	private Date usingDate;
	
	@OneToOne(mappedBy = "reservation",fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private RoomReview RoomReviewResSeq;
   @ManyToOne
   @JoinColumn(name="user_id")
   private Users userId;
   
   @ManyToOne//(cascade= {CascadeType.MERGE})
   @JoinColumn(name ="host_id", nullable = false)
   private HostUser hostUser; 
   
   @ManyToOne//(cascade= {CascadeType.MERGE})
   @JoinColumn(name ="room_seq", nullable = false)
   private RoomInfo roominfo;
   
   @Column(name = "start_time", nullable = false)
   private String startTime;
   
   @Column(name = "end_time", nullable = false)
   private String endTime;
   
   @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
   @Column(name = "using_date")
   private Date usingDate;
   
   @OneToOne(mappedBy = "reservation",fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
   private RoomReview RoomReviewResSeq;


}

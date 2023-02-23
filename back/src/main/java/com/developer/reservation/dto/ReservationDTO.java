package com.developer.reservation.dto;

import java.util.Date;

import com.developer.hostuser.entity.HostUser;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roomreview.entity.RoomReview;
import com.developer.users.entity.Users;


public class ReservationDTO {
	private Long resSeq;
	private Users userId;
	private HostUser hostUser; 
	private RoomInfo roominfo;
	private String startTime;
	private String endTime;
	private Date usingDate;
	
	private RoomReview roomReview;
}

package com.developer.reservation.dto;

import java.util.Date;

import com.developer.hostuser.dto.HostUserDTO;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roomreview.dto.RoomReviewDTO;
import com.developer.users.dto.UsersDTO;

import lombok.Data;

@Data
public class ReservationDTO {
	private Long resSeq;
	private String startTime;
	private String endTime;
	private Date usingDate;
	
	private RoomInfoDTO roomInfoDTO;
	private HostUserDTO hostUserDTO; 
	private UsersDTO usersDTO;
	private RoomReviewDTO roomReviewDTO;
}

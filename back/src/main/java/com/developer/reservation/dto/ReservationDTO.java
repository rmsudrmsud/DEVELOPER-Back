package com.developer.reservation.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.developer.hostuser.dto.HostUserDTO;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roomreview.dto.RoomReviewDTO;
import com.developer.users.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReservationDTO {
	private Long resSeq;
	private String startTime;
	private String endTime;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date usingDate;

	private RoomInfoDTO roomInfoDTO;
	private HostUserDTO hostUserDTO;
	private UsersDTO usersDTO;
	private RoomReviewDTO roomReviewDTO;

	// SR: 예약상세 & 목록 출력용
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class selectAllReservationDTO {
		private long resSeq;
		private String startTime;
		private String endTime;
		@Temporal(TemporalType.DATE)
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date usingDate;
		private String hostId;
		private String userId;
		private RoomInfoDTO.selectAllReservationDTO roomInfoDTO;
		private UsersDTO.selectAllReservationDTO usersDTO;
	}
	

}

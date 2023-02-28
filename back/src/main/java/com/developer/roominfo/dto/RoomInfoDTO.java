package com.developer.roominfo.dto;

import java.util.List;

import com.developer.reservation.dto.ReservationDTO;
import com.developer.reservation.entity.Reservation;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.entity.Studyroom;
import com.developer.users.dto.UsersDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RoomInfoDTO {
private long roomSeq;
	private String name;
	private String info;
	private String imgPath;
	private Integer person;
	private Integer price;

	private Studyroom studyroom;
	private List<Reservation> reservation; //원래 List<>타입
	
	//근형
	@Data
	@NoArgsConstructor
	public static class getReservationDTO{
		private String name;
		private ReservationDTO.getReservationDTO reservation;
		private UsersDTO.UsersNameDTO users;
	}
}

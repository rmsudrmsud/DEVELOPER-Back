package com.developer.hostuser.dto;

import com.developer.reservation.dto.ReservationDTO;
import com.developer.studyroom.dto.StudyroomDTO;

public class HostUserDTO {

	private String hostId;
	private String pwd;
	private String num;
	private Integer ready;
	private String name;
	private String tel;
	private String email;
	private StudyroomDTO studyroom;
	private ReservationDTO reservation; //원래는 List<>타입
}

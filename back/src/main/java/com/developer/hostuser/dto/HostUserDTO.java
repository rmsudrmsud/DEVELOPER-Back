package com.developer.hostuser.dto;

import com.developer.board.dto.BoardDTO;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.users.dto.UsersDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	
	//근형
	@Data
	@NoArgsConstructor
	public static class getAllHostUserDTO{
		private String hostId;
		private String pwd;
		private String num;
		private Integer ready;
		private String name;
		private String email;
	}
	//근형
	@Data
	@NoArgsConstructor
	public static class HostLoginDTO{
		private String hostId;
		private String pwd;
		private Integer ready;
	}
}



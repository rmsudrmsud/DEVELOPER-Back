package com.developer.roominfo.dto;

import java.util.List;
import com.developer.reservation.entity.Reservation;
import com.developer.studyroom.entity.Studyroom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfoDTO {
	private long roomSeq;
	private String name;
	private String info;
	private String imgPath;
	private Integer person;
	private Integer price;
	private Integer status;

	@JsonIgnore
	private Studyroom studyroom;
	@JsonIgnore
	private List<Reservation> reservation; // 원래 List<>타입

	// SR: 방목록 출력용
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class selectAllRoomDTO {
		private long roomSeq;
		private String name;
		private String info;
		private String imgPath;
		private Integer person;
		private Integer price;
	}
	
	// SR: 예약목록/상세 출력용
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class selectAllReservationDTO {
		private String name;
	}
}

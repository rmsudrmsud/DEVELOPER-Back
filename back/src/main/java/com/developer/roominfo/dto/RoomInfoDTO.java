package com.developer.roominfo.dto;

import java.util.List;

import com.developer.reservation.entity.Reservation;
import com.developer.studyroom.entity.Studyroom;

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
	
	private Studyroom studyroom;
	private List<Reservation> reservation; //원래 List<>타입
}

package com.developer.roomreview.dto;

import java.util.Date;

import com.developer.reservation.dto.ReservationDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RoomReviewDTO {

	private Long resSeq;
	private String content;
	private Integer star;
	private Date cDate;
	private ReservationDTO reservationDTO;

	// ds
	@Data
	@NoArgsConstructor
	public static class RoomReviewInsertDTO {
		private Long resSeq;
		private String content;
		private Integer star;
	}

	// ds
	@Data
	@NoArgsConstructor
	public static class RoomReviewSelectAllDTO {
		private Date cdate;
		private Integer star;
		private String content;

		private ReservationDTO.RoomReviewSelectAllDTO rrsaDTO;
	}

	// ds
	@Data
	@NoArgsConstructor
	public static class selectMyRmRvDTO {
		private Date cdate;
		private Integer star;
		private ReservationDTO.RoomReviewSelectMyRmRvDTO rrsaDTO;
	}

	// ds
	@Data
	@NoArgsConstructor
	public static class selectMyRmRvDetailDTO {
		private Date cdate;
		private Integer star;
		private String content;
		private ReservationDTO.RoomReviewSelectMyRmRvDetailDTO rrsaDTO;
	}
}
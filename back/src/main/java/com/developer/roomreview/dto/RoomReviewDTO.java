package com.developer.roomreview.dto;

import java.util.Date;

import com.developer.reservation.dto.ReservationDTO;

import lombok.Data;

@Data
public class RoomReviewDTO {

   private Long resSeq;
   private String content;
   private Integer star;
   private Date cDate;
   private ReservationDTO reservationDTO;
}
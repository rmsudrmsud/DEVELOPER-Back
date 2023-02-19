package com.developer.reservation;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.reservation.entity.Reservation;
@SpringBootTest
class ReservationRepository {
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private ReservationRepository res;
	
	@Test
	@DisplayName("예약add 기능 테스트")
	void testReservationAdd() {
		Reservation r = new Reservation();
		r.setUserId("kosta1");
		r.setHostId("suho522");
		r.setStartTime("10:00");
		r.setEndTime("12:00");
		Date date = new Date("2023/03/12");
		r.setUsingDate(date);
		ResRepository.
	}
	
	@Test
	@DisplayName("예약FindbyId 기능 테스트")
	void testReservationFindById() {
		Reservation r = new Reservation();
		
	}

}

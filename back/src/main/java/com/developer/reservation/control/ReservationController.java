package com.developer.reservation.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.reservation.service.ReservationService;

@RestController
@RequestMapping("reservation/*")
public class ReservationController {

	@Autowired
	private ReservationService rService;
	
	
}

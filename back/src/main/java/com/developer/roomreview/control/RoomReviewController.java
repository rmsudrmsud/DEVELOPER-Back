package com.developer.roomreview.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.roomreview.service.RoomReviewService;

@RestController
@RequestMapping("roomreview/*")
public class RoomReviewController {

	@Autowired
	private RoomReviewService rrservice;
	
	




	
	
}

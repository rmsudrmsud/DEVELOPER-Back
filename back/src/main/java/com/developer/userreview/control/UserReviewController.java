package com.developer.userreview.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.userreview.dto.UserReviewDTO;
import com.developer.userreview.service.UserReviewService;

@RestController
@RequestMapping("userreview/*")
public class UserReviewController {

	@Autowired
	private UserReviewService urService;
	
	@GetMapping(value = "tuteereview/{userId}")
	public ResponseEntity<?> getTuteeReview(@PathVariable String userId) throws FindException{
		List<UserReviewDTO.getTuteeReview> list = urService.getTuteeReview(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}

package com.developer.userreview.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.userreview.dto.UserReviewDTO;
import com.developer.userreview.service.UserReviewService;

@RestController
@RequestMapping("userreview/*")
public class UserReviewController {
	@Autowired
	private UserReviewService userReviewService;
	
	/**
	 * 튜티가 튜터에게받은수업후기 
	 * @author choigeunhyeong
	 * @param addReviewDTO
	 * @param applySeqRv
	 * @return
	 * @throws AddException
	 */
	@PostMapping(value="addreview/{applySeqRv}")
	public ResponseEntity<?> addReview(UserReviewDTO addReviewDTO, @PathVariable Long applySeqRv) throws AddException{
		userReviewService.addUserReview(addReviewDTO, applySeqRv);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

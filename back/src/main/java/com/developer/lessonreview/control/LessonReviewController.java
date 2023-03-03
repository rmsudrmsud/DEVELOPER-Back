package com.developer.lessonreview.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.lessonreview.service.LessonReviewService;

@RestController
@RequestMapping("lessonreview/*")
public class LessonReviewController {
	@Autowired
	private LessonReviewService lrservice;
	

	/**
	 *  해당 튜터의 후기 개수
	 *  @author moonone
	 * @param tutorId 튜티아이디
	 * @return 개수
	 * @throws FindException
	 */
	@GetMapping(value = "{tutorId}")
	public ResponseEntity<?> cntReview(@PathVariable String tutorId) throws FindException{		
		int cnt = lrservice.cntReview(tutorId);
		return new ResponseEntity<>(cnt, HttpStatus.OK);
	}
}

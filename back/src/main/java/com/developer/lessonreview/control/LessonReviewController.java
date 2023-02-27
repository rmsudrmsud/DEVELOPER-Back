package com.developer.lessonreview.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.lessonreview.entity.LessonReview;
import com.developer.lessonreview.service.LessonReviewService;

@RestController
@RequestMapping("lessonreview/*")
public class LessonReviewController {
//	@Autowired
//	private LessonReviewService service;
//	
//	//[JW] 튜터의 수업에 대한 후기 작성 
//	@PostMapping(value = "add")
//	public ResponseEntity<?> addReview(@RequestBody LessonReview lessonReview) throws AddException, FindException{
//		service.addReview(lessonReview);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	

}

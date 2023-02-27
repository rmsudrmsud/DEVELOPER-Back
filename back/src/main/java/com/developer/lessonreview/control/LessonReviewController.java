package com.developer.lessonreview.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.lessonreview.service.LessonReviewService;

@RestController
@RequestMapping("lessonreview/*")
public class LessonReviewController {
	@Autowired
	private LessonReviewService service;
	
	//[JW] 튜터의 수업에 대한 후기 작성  및 수정
	@PostMapping
	public ResponseEntity<?> addReview(@RequestBody LessonReviewDTO.lrDTO lrDTO) throws AddException, FindException{
		service.addReview(lrDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//[JW] 해당 튜터의 후기 개수
	@GetMapping(value = "{tutorId}")
	public ResponseEntity<?> cntReview(@PathVariable String tutorId) throws FindException{		
		int cnt = service.cntReview(tutorId);
		return new ResponseEntity<>(cnt, HttpStatus.OK);
	}
	
	//[JW] 작성한 후기 목록 확인  
	@GetMapping
	public ResponseEntity<?> lReviewList(HttpSession session) throws FindException{
		String logined = "tutee2";
//		String logined = (String)session.getAttribute("logined");
		List<LessonReviewDTO.listLRListDTO> list	= service.lReviewList(logined);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}

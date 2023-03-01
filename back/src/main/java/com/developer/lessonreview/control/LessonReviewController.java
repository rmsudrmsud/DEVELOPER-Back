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
	private LessonReviewService lrservice;
	

	/**
	 *  튜터의 수업에 대한 후기 추가 및 수정
	 *  @author moonone
	 * @param lrDTO 작성한 후기
	 * @throws FindException
	 */
	@PostMapping(value = "review")
	public ResponseEntity<?> addReview(@RequestBody LessonReviewDTO.lrDTO lrDTO) throws AddException, FindException{
		lrservice.addReview(lrDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 *  해당 튜터의 후기 개수
	 * @param tutorId 튜티아이디
	 * @return 개수
	 * @throws FindException
	 */
	@GetMapping(value = "{tutorId}")
	public ResponseEntity<?> cntReview(@PathVariable String tutorId) throws FindException{		
		int cnt = lrservice.cntReview(tutorId);
		return new ResponseEntity<>(cnt, HttpStatus.OK);
	}
	

	/**
	 * 작성한 후기 목록 확인  
	 * @author moonone
	 * @param userId 사용자아이디
	 * @return 후기목록
	 * @throws FindException
	 */
	@GetMapping(value="lesson")
	public ResponseEntity<?> lReviewList(HttpSession session) throws FindException{
		String logined = (String)session.getAttribute("logined");
		List<LessonReviewDTO.listLRListDTO> list	= lrservice.lReviewList(logined);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	

	/**
	 * 후기를 작성하지 않은 수업 목록 확인
	 * @author moonone
	 * @param userId 사용자아이디 
	 * @return 수업목록
	 * @throws FindException
	 */
	@GetMapping
	public ResponseEntity<?> noWriteLReview(HttpSession session) throws FindException{
		String logined = (String)session.getAttribute("logined");
		List<LessonReviewDTO.noWriteLReviewDTO> list	= lrservice.noWriteLReview(logined);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}

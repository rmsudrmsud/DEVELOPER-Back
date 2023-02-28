package com.developer.lesson.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonRequestDTO;
import com.developer.lesson.service.LessonService;

@RestController
@RequestMapping("lesson/*")
public class LessonController {
	@Autowired
	private LessonService service;
	
	/**
	 * [메인페이지] 신청종료날짜 임박순으로 list를 출력한다.
	 * @author SR
	 * @return 신청종료남짜 임박순으로 정렬한 list
	 * @throws FindException
	 */
	@GetMapping(value ="listbydate", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<?> ListLessonByDateController() throws FindException{
		List<LessonRequestDTO.selectAllBydateLessonDTO> list = service.selectAllByDateLesson();
		if(list.isEmpty()) {
			return new ResponseEntity<>("신청진행중인 수업이 없습니다.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}

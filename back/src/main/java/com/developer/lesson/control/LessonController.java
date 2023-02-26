package com.developer.lesson.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonRequestDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.service.LessonService;

@RestController
@RequestMapping("lesson/*")
public class LessonController {
	@Autowired
	private LessonService service;
	
	// [JW] 선택한 클래스에 대한 상세 정보
	@GetMapping(value = {"{lessonSeq}"}, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<?> detail(@PathVariable Long lessonSeq) throws FindException{
		Lesson lesson = service.selectDetail(lessonSeq);
		return new ResponseEntity<>(lesson, HttpStatus.OK);
	}
	
	// [JW] 수업 등록
	@PostMapping
	public ResponseEntity<LessonRequestDTO> add(@RequestBody LessonRequestDTO dto) throws AddException, FindException{
		service.addLesson(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * [메인페이지] 신청종료날짜 임박순으로 list를 출력한다.
	 * @author SR
	 * @return 신청종료남짜 임박순으로 정렬한 list
	 * @throws FindException
	 */
	@GetMapping(value ="listbydate", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<?> ListLessonByDateController() throws FindException{
		List<LessonRequestDTO.selectAllBydateLessonDTO> list = service.selectAllByDateLesson();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}

package com.developer.lesson.control;

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
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.service.LessonService;

@RestController
@RequestMapping("lesson/*")
public class LessonController {
	@Autowired
	private LessonService service;
	
	// [JW] 선택한 클래스에 대한 상세 정보
	@GetMapping(value = {"{lessonSeq}"}, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<LessonDTO> detail(@PathVariable Long lessonSeq) throws FindException{
		LessonDTO lessonDto = service.selectDetail(lessonSeq);
		return new ResponseEntity<LessonDTO>(lessonDto, HttpStatus.OK);
	}
	
	// [JW] 수업 등록
	@PostMapping
	public ResponseEntity<LessonDTO> add(@RequestBody LessonDTO dto) throws AddException, FindException{
		service.addLesson(null, null);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

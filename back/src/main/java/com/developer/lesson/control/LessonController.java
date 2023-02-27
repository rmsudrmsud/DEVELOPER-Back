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
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.dto.LessonDTO.selectDetailDTO;
import com.developer.lesson.service.LessonService;

@RestController
@RequestMapping("lesson/*")
public class LessonController {
	@Autowired
	private LessonService service;
	
//	 [JW] 선택한 클래스에 대한 상세 정보
	@GetMapping(value = {"detail/{lessonSeq}"}, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<LessonDTO.selectDetailDTO> detail(@PathVariable Long lessonSeq) throws FindException{
		selectDetailDTO lessonDto = service.selectDetail(lessonSeq);
		return new ResponseEntity<LessonDTO.selectDetailDTO>(lessonDto, HttpStatus.OK);
	}
	
	// [JW] 수업 등록
	@PostMapping(value = {"{userId}"}, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<LessonDTO> add(@RequestBody LessonDTO dto, @PathVariable String userId) throws AddException, FindException{
		service.addLessonDTO(dto, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// [JW] 클래스를 개설한 튜터의 후기 목록
	@GetMapping(value = {"{lessonSeq}"})
	public ResponseEntity<?> review(@PathVariable Long lessonSeq) throws FindException{
		List<LessonDTO.selectAllReviewDTO> list = service.selectAllReview(lessonSeq);
		return new ResponseEntity<>(list ,HttpStatus.OK);
	}
	
}

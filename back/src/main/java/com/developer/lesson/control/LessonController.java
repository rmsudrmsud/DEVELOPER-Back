package com.developer.lesson.control;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.service.LessonService;

@RestController
@RequestMapping("lesson/*")
public class LessonController {
	@Autowired
	private LessonService lservice;
	
	@GetMapping(value = "getlesson1/{tutorId}" )
	public ResponseEntity<?> getLessonByUser1(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser> list = lservice.getLessonByUser1(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	@GetMapping(value = "getlesson2/{tutorId}" )
	public ResponseEntity<?> getLessonByUser2(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser> list = lservice.getLessonByUser2(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	@GetMapping(value = "getlesson3/{tutorId}" )
	public ResponseEntity<?> getLessonByUser3(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser> list = lservice.getLessonByUser3(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	@GetMapping(value = "getapply/{userId}")
	public ResponseEntity<?> getApplyLesson(@PathVariable String userId) throws FindException{
		List<LessonDTO.applyLessonBytutee> list = lservice.getApplyLesson(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "upcoming/{userId}")
	public ResponseEntity<?> upComingLesson(@PathVariable String userId) throws FindException{
		List<LessonDTO.applyLessonBytutee> list = lservice.upComingLesson(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "ongoing/{userId}")
	public ResponseEntity<?> onGoinLesson(@PathVariable String userId) throws FindException{
		List<LessonDTO.applyLessonBytutee> list = lservice.onGoingLesson(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(value = "lessondetail/{lessonSeq}")
	public ResponseEntity<?> getLessonDetail(@PathVariable Long lessonSeq) throws FindException{
		List<LessonDTO.selectDetailDTO> list = lservice.getLessonDetail(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "selectlesson/{lessonSeq}")
	public ResponseEntity<?> selectLesson(@PathVariable Long lessonSeq) throws FindException{
		if(lessonSeq == null) {
			return new ResponseEntity<>("수업이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			LessonDTO.selectDetailDTO lessonDTO = lservice.selectLesson(lessonSeq);
			return new ResponseEntity<>(lessonDTO, HttpStatus.OK);
		}
	}
	
	@PatchMapping(value = "update/{lessonSeq}")
	public ResponseEntity<?> updateLesson(@PathVariable Long lessonSeq,@RequestBody LessonDTO.selectDetailDTO lDTO, HttpSession session) throws FindException {
		lservice.updates(lDTO);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	@PatchMapping(value = "delete/{lessonSeq}")
	public ResponseEntity<?> deleteLesson(@PathVariable Long lessonSeq, HttpSession session) throws FindException{
		if(lessonSeq == null) {
			return new ResponseEntity<>("수업 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			lservice.deleteLesson(lessonSeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
}

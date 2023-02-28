package com.developer.appliedlesson.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.service.AppliedLessonService;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;

@RestController
@RequestMapping("appliedlesson/*")
public class AppliedLessonController {

	@Autowired
	private AppliedLessonService alService;
	
	@GetMapping(value = {"{applySeq}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAppliedLesson(@PathVariable Long applySeq, HttpSession session) throws FindException{
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			AppliedLessonDTO.selectAppliedLessonDTO appliedLessonDTO = alService.selectAppliedLesson(applySeq);
			return new ResponseEntity<>(appliedLessonDTO, HttpStatus.OK);			
		}
		
	}
	
	@PatchMapping(value = {"apply/{applySeq}"})
	public ResponseEntity<?> updateApplyLesson(@PathVariable Long applySeq, HttpSession session) throws FindException {
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청 내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			alService.updateApplyLesson(applySeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	@PatchMapping(value = {"notapply/{applySeq}"})
	public ResponseEntity<?> updateNotApplyLesson(@PathVariable Long applySeq, HttpSession session) throws FindException {
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청 내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			alService.updateNotApplyLesson(applySeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	@GetMapping(value = "notapply/{lessonSeq}")
	public ResponseEntity<?> getLessonNotApplyUser(@PathVariable Long lessonSeq) throws FindException{
		List<AppliedLessonDTO.UserByAppliedLessonDTO> list = alService.getLessonNotApplyUser(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	@GetMapping(value = "apply/{lessonSeq}")
	public ResponseEntity<?> getLessonApplyUser(@PathVariable Long lessonSeq) throws FindException{
		List<AppliedLessonDTO.UserByAppliedLessonDTO> list = alService.getLessonApplyUser(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	
}

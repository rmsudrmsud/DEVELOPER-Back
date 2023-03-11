package com.developer.appliedlesson.control;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.service.AppliedLessonService;
import com.developer.exception.AddException;
import com.developer.exception.FindException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("appliedlesson/*")
@RequiredArgsConstructor
public class AppliedLessonController {

	private final AppliedLessonService alService;

	/**
	 * 수업 신청
	 * 
	 * @author moonone
	 * @param alDTO
	 * @param session
	 * @return
	 * @throws AddException
	 * @throws FindException
	 */
	@PostMapping(value = "{lessonSeq}")
	public ResponseEntity<?> applyLesson(@RequestBody AppliedLessonDTO.alAddRequestDTO alDTO, HttpSession session,
			@PathVariable Long lessonSeq) throws AddException, FindException {
		String logined = (String) session.getAttribute("logined");
		if (logined != null) {
			alService.applyLesson(alDTO, lessonSeq, logined);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

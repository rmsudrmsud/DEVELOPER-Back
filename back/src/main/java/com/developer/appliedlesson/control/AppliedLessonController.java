package com.developer.appliedlesson.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.appliedlesson.service.AppliedLessonService;
import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;

@RestController
@RequestMapping("appliedlesson/*")
public class AppliedLessonController {
	@Autowired
	private AppliedLessonService appliedLessonService;
	
	/**
	 * 진행완료된 클래스 페이지  클래스명, 수강했던 튜티목록
	 * @author choigeunhyeong
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value="classandtutee/{lessonSeq}")
	public ResponseEntity<?> selectClassAndTutee(@PathVariable Long lessonSeq) throws FindException{
		List<UsersDTO.getNameDTO> list = appliedLessonService.selectClassAndTutee(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 진행완료된 클래스 페이지 후기 전체목록 
	 * @author choigeunhyeong
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value="completedclass/{userId}")
	public ResponseEntity<?> selectCompletedClassList(@PathVariable String userId) throws FindException{
		List<UsersDTO.getCompletedClassDTO> list = appliedLessonService.selectCompletedClassList(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}

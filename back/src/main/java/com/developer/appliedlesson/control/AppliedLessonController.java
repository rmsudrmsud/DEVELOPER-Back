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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.service.AppliedLessonService;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;

@RestController
@RequestMapping("appliedlesson/*")
public class AppliedLessonController {

	@Autowired
	private AppliedLessonService alService;
	
	/**
	 * 진행완료된 클래스 페이지  클래스명, 수강했던 튜티목록
	 * @author choigeunhyeong
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value="classandtutee/{lessonSeq}")
	public ResponseEntity<?> selectClassAndTutee(@PathVariable Long lessonSeq) throws FindException{
		List<UsersDTO.getNameDTO> list = alService.selectClassAndTutee(lessonSeq);
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
		List<UsersDTO.getCompletedClassDTO> list = alService.selectCompletedClassList(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * 수업 신청
	 * @author moonone
	 * @param alDTO
	 * @param session
	 * @return
	 * @throws AddException
	 * @throws FindException
	 */
	@PostMapping(value = "{lessonSeq}")
	public ResponseEntity<?> applyLesson(@RequestBody AppliedLessonDTO.alAddRequestDTO alDTO, HttpSession session, @PathVariable Long lessonSeq) throws AddException, FindException{
		String logined = (String)session.getAttribute("logined");
		if(logined != null) {
			alService.applyLesson(alDTO, lessonSeq, logined);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

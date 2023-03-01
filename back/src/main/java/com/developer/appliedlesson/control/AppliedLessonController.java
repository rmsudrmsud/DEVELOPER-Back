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

	//[JH]
	@GetMapping(value = "{applySeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAppliedLesson(@PathVariable Long applySeq, HttpSession session) throws FindException{
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			AppliedLessonDTO.selectAppliedLessonDTO appliedLessonDTO = alService.selectAppliedLesson(applySeq);
			return new ResponseEntity<>(appliedLessonDTO, HttpStatus.OK);			
		}
		
	}
	
	//[JH]
	@PatchMapping(value = "apply/{applySeq}")
	public ResponseEntity<?> updateApplyLesson(@PathVariable Long applySeq, HttpSession session) throws FindException {
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청 내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			alService.updateApplyLesson(applySeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	//[JH]
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
	
	//[JH]
	@GetMapping(value = "notapply/{lessonSeq}")
	public ResponseEntity<?> getLessonNotApplyUser(@PathVariable Long lessonSeq) throws FindException{
		List<AppliedLessonDTO.UserByAppliedLessonDTO> list = alService.getLessonNotApplyUser(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	//[JH]
	@GetMapping(value = "apply/{lessonSeq}")
	public ResponseEntity<?> getLessonApplyUser(@PathVariable Long lessonSeq) throws FindException{
		List<AppliedLessonDTO.UserByAppliedLessonDTO> list = alService.getLessonApplyUser(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	

	
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

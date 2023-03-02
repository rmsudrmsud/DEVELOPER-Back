package com.developer.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.service.HostUserService;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.service.LessonService;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roominfo.service.RoomInfoService;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.service.StudyroomService;
import com.developer.tutor.service.TutorService;
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("admin/*")
@RequiredArgsConstructor
public class AdminController {
	private final LessonService lservice;
	private final UsersService uService;
	private final TutorService tservice;
	private final HostUserService hService;
	private final RoomInfoService riService;
	private final StudyroomService sService;

	/**
	 * [관리자] 모든 수업 목록
	 * @author moonone
	 * @return 수업 목록
	 */
	@GetMapping(value = "lesson", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> allLessonList(HttpSession session) throws FindException {
		int role = (int) session.getAttribute("loginedRole");
		if(role == 9) {
			List<LessonDTO.allLessonListDTO> list = lservice.allLessonList();
			return new ResponseEntity<>(list, HttpStatus.OK);			
		} else {
			return new ResponseEntity<>("관리자로 로그인하시오", HttpStatus.BAD_REQUEST);						
		}
	}

	/**
	 * [관리자] 수업을 예약한 튜티 목록
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	@GetMapping(value = "lesson/detail/{lessonSeq}")
	public ResponseEntity<?> applyTuteeList(@PathVariable Long lessonSeq) {
		List<UsersDTO.uNameDTO> list = uService.applyTuteeList(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * [관리자] 수업을 예약한 튜티 삭제
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 * @throws RemoveException 
	 */
	@DeleteMapping(value = "lesson/detail/{lessonSeq}/{tuteeId}")
	public ResponseEntity<?> deleteTutee(@PathVariable Long lessonSeq, @PathVariable String tuteeId) throws RemoveException {
		uService.deleteTutee(tuteeId, lessonSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

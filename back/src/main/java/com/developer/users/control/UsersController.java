package com.developer.users.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;

@RestController
@RequestMapping("users/*")
public class UsersController {
	@Autowired
	private UsersService uService;
	
	/**
	 * [관리자] 수업을 예약한 튜티 목록
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	@GetMapping(value="{lessonSeq}")
	public ResponseEntity<?> applyTuteeList(@PathVariable Long lessonSeq){
		List<UsersDTO.uNameDTO> list = uService.applyTuteeList(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * [관리자] 수업을 예약한 튜티 삭제
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	@DeleteMapping(value="{lessonSeq}")
	public ResponseEntity<?> deleteTutee(@PathVariable Long lessonSeq, @RequestParam String tuteeId){
		uService.deleteTutee(tuteeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 튜터로 승인한다.
	 * @author SR
	 * @param userId
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "ok/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorApply(@PathVariable String userId, HttpSession session) throws FindException {
		userId = "3";
		// userId = (String) session.getAttribute("logined");
		if (userId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			uService.tutorApply(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	/**
	 * 튜터 미승인 목록을 출력한다.
	 * @author SR
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value="unapprovelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorUnapproveList() throws FindException {
		
		List<UsersDTO.unapproveTutorDTO> list = uService.selectAllUnapproveTutor();
		if(list.isEmpty()) {
			return new ResponseEntity<>("미승인한 튜터가 없습니다", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}

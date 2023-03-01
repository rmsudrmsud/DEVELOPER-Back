package com.developer.tutor.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.tutor.dto.TutorDTO;
import com.developer.tutor.service.TutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tutor/*")
@RequiredArgsConstructor
public class TutorController {
	@Autowired
	private TutorService tservice;

	/**
	 * 튜터 등록 및 수정
	 * 
	 * @author moonone
	 * @param tDTO
	 * @return 상태값
	 * @throws AddException
	 * @throws FindException
	 */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody TutorDTO.saveTutorDTO tDTO, HttpSession session)
			throws AddException, FindException {
		tservice.saveTutor(tDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 튜터가 생성한 클래스 목록 + 튜터 정보
	 * 
	 * @author moonone
	 * @param tutorId
	 * @return 튜터가 생성한 클래스 목록 + 튜터 정보
	 * @throws AddException
	 * @throws FindException
	 */
	@GetMapping(value = "{tutorId}")
	public ResponseEntity<?> selectTutorDetail(@PathVariable String tutorId) throws AddException, FindException {
		List<TutorDTO.selectTutorDetailDTO> list = tservice.selectTutorDetail(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * 튜터 승인거절
	 * 
	 * @author SR
	 * @param userId
	 * @param session
	 * @return
	 * @throws RemoveException
	 */

	@DeleteMapping(value= "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorReject(@PathVariable String userId, HttpSession session) throws RemoveException {
		tservice.deleteTutor(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

package com.developer.tutor.control;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody TutorDTO.saveTutorDTO tDTO, HttpSession session) throws AddException, FindException {
		String logined = (String) session.getAttribute("logined");
		if(logined != null) {
			tservice.saveTutor(tDTO, logined);			
			return new ResponseEntity<>("등록 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("로그인하세요", HttpStatus.BAD_REQUEST);			
		}
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


}

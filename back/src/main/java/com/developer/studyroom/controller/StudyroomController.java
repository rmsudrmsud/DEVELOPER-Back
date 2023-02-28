package com.developer.studyroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.service.StudyroomService;

@RestController
@RequestMapping("studyroom/*")
public class StudyroomController {
	@Autowired
	private StudyroomService studyroomservice;
	
	
	 /**
	  * 관리자 스터디카페 전체목록 출력
	  * @author choigeunhyeong
	  * @return
	  * @throws FindException
	  */
	@GetMapping(value = "getall")
	public ResponseEntity<?> getAllStudyroom() throws FindException{
		List<StudyroomDTO.getAllStudyroomDTO> list= studyroomservice.getAllStudyroom();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "detail/{srSeq}")
	public ResponseEntity<?> detailStudyroom(@PathVariable Long srSeq) throws FindException{
		Studyroom s = studyroomservice.detailStudyroom(srSeq);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

}

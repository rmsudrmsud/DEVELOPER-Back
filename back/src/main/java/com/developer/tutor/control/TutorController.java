package com.developer.tutor.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.tutor.dto.TutorDTO;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.service.TutorService;

@RestController
@RequestMapping("tutor/*")
public class TutorController {
	@Autowired
	private TutorService service;
	
	//[JW] 튜터등록 
	@PostMapping
	public ResponseEntity<?> save(@RequestBody TutorDTO.saveTutorDTO tDTO) throws AddException, FindException{
		service.saveTutor(tDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

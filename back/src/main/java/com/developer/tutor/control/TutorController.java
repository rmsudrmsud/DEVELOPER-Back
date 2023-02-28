package com.developer.tutor.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.service.TutorService;

@RestController
@RequestMapping("tutor/*")
public class TutorController {
	@Autowired
	private TutorService service;
	
	//[JW] 튜터등록 
	//TODO: Restful 방식대로라면 ... 여기에 value 값을 안 줄수는 없는 건가 ?
	@PostMapping("") //주소에 /에 넣어줘야함
	public ResponseEntity<?> add(@RequestBody Tutor tutor) throws AddException, FindException{
		service.addTutor(tutor);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value= "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorReject(@PathVariable String userId, HttpSession session) throws RemoveException {
		service.deleteTutor(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

package com.developer.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;

@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(FindException.class)
	@ResponseBody
	public ResponseEntity<?> findExceptionHandler(Exception e) {
		System.out.println("---------------findControllerAdvice----------------");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset-UTF-8"); //한글깨짐방지를 위해 HttpHeader설정
		e.printStackTrace();
		Map<String, String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		System.out.println("---------------findControllerAdvice----------------");
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//예외처리용 부가로직 
	@ExceptionHandler(AddException.class)
	@ResponseBody
	public ResponseEntity<?> addExceptionHandler(AddException e){
		System.out.println("======AddException, ControllerAdvice=======");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset-UTF-8"); //한글깨짐방지를 위해 HttpHeader설정
		e.printStackTrace();
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//예외처리용 부가로직 
	@ExceptionHandler(RemoveException.class)
	@ResponseBody
	public ResponseEntity<?> RemoveExceptionHandler(FindException e){
		System.out.println("======RemoveException, ControllerAdvice=======");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset-UTF-8"); //한글깨짐방지를 위해 HttpHeader설정
		e.printStackTrace();
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}





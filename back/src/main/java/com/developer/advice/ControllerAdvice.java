package com.developer.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;

public class ControllerAdvice {
	@ExceptionHandler(FindException.class)
	@ResponseBody
	public ResponseEntity<?> findExceptionHandler(Exception e) {
		System.out.println("---------------FindControllerAdvice----------------");
		e.printStackTrace();
		Map<String, String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(RemoveException.class)
	@ResponseBody
	public ResponseEntity<?> RemoveExceptionHandler(Exception e) {
		System.out.println("---------------RemoveControllerAdvice----------------");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");
		return new ResponseEntity<>(e.getMessage(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

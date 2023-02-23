package com.developer.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.developer.exception.FindException;

public class ControllerAdvice {
	@ExceptionHandler(FindException.class)
	@ResponseBody
	public ResponseEntity<?> findExceptionHandler(Exception e) {
		System.out.println("---------------findControllerAdvice----------------");
		e.printStackTrace();
		Map<String, String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

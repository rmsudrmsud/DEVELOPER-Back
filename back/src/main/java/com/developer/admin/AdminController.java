package com.developer.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.service.HostUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("admin/*")
@RequiredArgsConstructor
public class AdminController {

	private final HostUserService hService;


	//[JH] 호스트유저 전체출력
	@GetMapping(value = "host")
	private ResponseEntity<?> selectAllHostUser() throws FindException{
		List<HostUser> list = hService.selectAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}

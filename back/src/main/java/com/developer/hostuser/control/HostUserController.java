package com.developer.hostuser.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.service.HostUserService;

@RestController
@RequestMapping("hostuser/*")
public class HostUserController {
	
	@Autowired
	private HostUserService hostUserService;

	@PostMapping(value = "")
	public ResponseEntity<?> addHost(@RequestBody HostUserDTO hostDTO) throws AddException{
		hostUserService.addHost(hostDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "selectall")
	private ResponseEntity<?> selectAllHostUser() throws FindException{
		List<HostUser> list = hostUserService.selectAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}

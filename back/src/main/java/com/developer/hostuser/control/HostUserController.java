package com.developer.hostuser.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.hostuser.service.HostUserService;

@RestController
@RequestMapping("hostuser/*")
public class HostUserController {
	
	@Autowired
	private HostUserService hostUserService;
	
	//@GetMapping(value = { "list/{currentPage}/{word}","list/{currentPage}", "list" }, produces = MediaType.APPLICATION_JSON_VALUE)

}

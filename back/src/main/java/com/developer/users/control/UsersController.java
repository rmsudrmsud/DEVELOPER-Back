package com.developer.users.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.users.service.UsersService;

@RestController
@RequestMapping("users/*")
public class UsersController {

	@Autowired
	private UsersService uService;
}

package com.developer.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/emailConfirm")
	public String emailConfirm(@RequestParam String email) throws Exception {

	  String confirm = emailService.sendSimpleMessage(email);
	  return confirm;
	}
}

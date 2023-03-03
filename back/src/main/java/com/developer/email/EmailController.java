package com.developer.email;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.email.EmailDTO.MailDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("email/*")
public class EmailController {
	
	private final EmailService emailService;
	
	
	/**
	 * 이메일 발송 : front에 반환한 인증 코드와 서버 터미널에 찍힌 인증 코드가 같은지 확인 필요함
	 * @author SR
	 * @param email
	 * @return 난수값
	 * @throws Exception
	 */
	@PostMapping("/emailConfirm")
	public String emailConfirm(@RequestParam String email) throws Exception {

	  String confirm = emailService.sendSimpleMessage(email);
	  return confirm;
	}
	

}

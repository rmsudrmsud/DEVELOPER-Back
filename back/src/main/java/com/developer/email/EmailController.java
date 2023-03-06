package com.developer.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.developer.tutor.service.TutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("email/*")
public class EmailController {
	
	private final EmailService emailService;
	private final TutorService tService;
	
	/**
	 * 본인인증 메일 : front에 반환한 인증 코드와 서버 터미널에 찍힌 인증 코드가 같은지 확인 필요함
	 * @author SR
	 * @param email
	 * @return 난수값
	 * @throws Exception
	 */
	@PostMapping(value="emailConfirm")
	@ResponseBody
	public Map<String, Object> emailConfirm(@RequestParam String email) throws Exception {
	  Map<String, Object> map = new HashMap<>();
	  String confirm = emailService.sendSimpleMessage(email);
	  System.out.println("컨트롤러confirm인증키:" + confirm);
	  map.put("key", confirm);
	  return map;
	}
	
}

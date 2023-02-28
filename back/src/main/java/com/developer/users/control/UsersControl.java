 package com.developer.users.control;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.repository.UsersRepository;
import com.developer.users.service.UsersService;

@RestController
@RequestMapping("users/*")
public class UsersControl {
	
	@Autowired
	private UsersService service;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UsersRepository usersReporitory;
	
	/**
	 * 로그인 체크
	 * @param session
	 * @return
	 */
	@GetMapping(value="checklogined", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checklogined(HttpSession session) {
		String logined = (String)session.getAttribute("logined");
		if(logined != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>("로그인이 안된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 로그인
	 * @author choigeunhyeong
	 * @param id
	 * @param pwd
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestParam String userId, String pwd, HttpSession session) throws FindException	{
		UsersDTO.uDTO usersDTO = service.userLogin(userId, pwd);
		session.setAttribute("logined", usersDTO.getUserId());
//		session.setAttribute("logined", usersDTO.getRole());
		//System.out.println("로그인성공시 sessionid : " + session.getId());
		logger.info("로그인성공시 sessionid : " + session.getId());
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	/**
	 * 로그아웃
	 * @author choigeunhyeong
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		logger.info("로그아웃시 sessionid : " + session.getId());
		session.invalidate();
		return "";
	}
	
	/**
	 * [관리자] 수업을 예약한 튜티 목록
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	@GetMapping(value="{lessonSeq}")
	public ResponseEntity<?> applyTuteeList(@PathVariable Long lessonSeq){
		List<UsersDTO.uNameDTO> list = service.applyTuteeList(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * [관리자] 수업을 예약한 튜티 삭제
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	@DeleteMapping(value="{lessonSeq}")
	public ResponseEntity<?> deleteTutee(@PathVariable Long lessonSeq, @RequestParam String tuteeId){
		service.deleteTutee(tuteeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

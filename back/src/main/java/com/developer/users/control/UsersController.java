package com.developer.users.control;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;

@RestController
@RequestMapping("users/*")
public class UsersController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UsersService uService;
	
	//[JH]
	@PostMapping(value = "")
	public ResponseEntity<?> addUsers(@RequestBody UsersDTO usersDTO) throws AddException{
		uService.addUsers(usersDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//[JH]
	@PatchMapping(value = "update/{userId}")
	public ResponseEntity<?> updateLesson(@PathVariable String userId, @RequestBody UsersDTO uDTO) throws FindException, AddException{
		uService.addUsers(uDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//[JH]
	@GetMapping(value = "selectuser/{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId) throws FindException{
		if(userId == null) {
			return new ResponseEntity<>("존재하지 않는 회원입니다.",HttpStatus.BAD_REQUEST);
		} else {
			UsersDTO usersDTO = uService.getUser(userId);
			return new ResponseEntity<>(usersDTO, HttpStatus.OK);
		}
	}
	
	//[JH]
	@PatchMapping(value = "delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId, HttpSession session) throws FindException{
		if(userId == null) {
			return new ResponseEntity<>("수업 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			uService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}	

	/**
	 * [관리자] 수업을 예약한 튜티 목록
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	@GetMapping(value="{lessonSeq}")
	public ResponseEntity<?> applyTuteeList(@PathVariable Long lessonSeq){
		List<UsersDTO.uNameDTO> list = uService.applyTuteeList(lessonSeq);
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
		uService.deleteTutee(tuteeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 튜터로 승인한다.
	 * @author SR
	 * @param userId
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "ok/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorApply(@PathVariable String userId, HttpSession session) throws FindException {
		userId = "3";
		// userId = (String) session.getAttribute("logined");
		if (userId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			uService.tutorApply(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	//[JH]
	@GetMapping(value = "check/{userId}")
	public boolean checkUser(@PathVariable String userId,HttpSession session) throws FindException{
		UsersDTO usersDTO;
		boolean flag = true;
		usersDTO = uService.getUser(userId);
		String check = usersDTO.getUserId();
			if (check == null) {
				flag = true;
			} else if (check != null) {
				flag = false;
			}
			return flag;
	}

	/**
	 * 튜터 미승인 목록을 출력한다.
	 * @author SR
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value="unapprovelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorUnapproveList() throws FindException {
		
		List<UsersDTO.unapproveTutorDTO> list = uService.selectAllUnapproveTutor();
		if(list.isEmpty()) {
			return new ResponseEntity<>("미승인한 튜터가 없습니다", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 로그인 체크 
	 * @author choigeunhyeong
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
	@PostMapping(value = "userlogin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestParam String userId, String pwd, HttpSession session) throws FindException	{
		UsersDTO.uDTO usersDTO = uService.userLogin(userId, pwd);
		session.setAttribute("logined", usersDTO.getUserId());
		session.setAttribute("loginedRole", usersDTO.getRole());
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
	 * 관리자페이지에서 회원리스트 전체출력
	 * @author DS
	 * @return 회원리스트
	 * @throws FindException
	 */
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers()throws FindException{
	 List<UsersDTO> list	= uService.getALLUsers();
	 return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**관리자페이지 회원리스트에서 검색하기
	 * @author DS
	 * @param userId
	 * @return 검색된 회원정보
	 * @throws FindException
	 */
	@GetMapping(value = "all/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getuserById(@PathVariable String userId)throws FindException{
	 List<UsersDTO> list	= uService.getUserById(userId);
	 return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}

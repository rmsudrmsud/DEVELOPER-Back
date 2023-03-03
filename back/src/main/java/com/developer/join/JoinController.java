package com.developer.join;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.service.HostUserService;
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("join/*")
@RequiredArgsConstructor
public class JoinController {

	private final UsersService uService;
	private final HostUserService hService;
	
	//[JH] 호스트 회원가입
	@PostMapping(value = "hostuser")
	public ResponseEntity<?> addHost(@RequestBody HostUserDTO hostDTO) throws AddException{
		hService.addHost(hostDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//[JH] 사용자 회원가입
	@PostMapping(value = "users")
	public ResponseEntity<?> addUsers(@RequestBody UsersDTO usersDTO) throws AddException{
		uService.addUsers(usersDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//[JH] 사용자 아이디 중복체크
	@GetMapping(value = "users/check/{userId}")
	public boolean checkUser(@PathVariable String userId,HttpSession session) throws FindException{
		UsersDTO.UsersDetailDTO usersDTO;
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
	
}

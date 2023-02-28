package com.developer.users.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;
import com.developer.users.service.UsersService;

@RestController
@RequestMapping("users/*")
public class UsersController {

	@Autowired
	private UsersService uService;
	
	@PostMapping(value = "")
	public ResponseEntity<?> addUsers(@RequestBody UsersDTO usersDTO) throws AddException{
		uService.addUsers(usersDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping(value = "update/{userId}")
	public ResponseEntity<?> updateLesson(@PathVariable String userId, @RequestBody UsersDTO uDTO) throws FindException, AddException{
		uService.addUsers(uDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "selectuser/{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId) throws FindException{
		if(userId == null) {
			return new ResponseEntity<>("존재하지 않는 회원입니다.",HttpStatus.BAD_REQUEST);
		} else {
			UsersDTO usersDTO = uService.getUser(userId);
			return new ResponseEntity<>(usersDTO, HttpStatus.OK);
		}
	}
	
	@PatchMapping(value = "delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId, HttpSession session) throws FindException{
		if(userId == null) {
			return new ResponseEntity<>("수업 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			uService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
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
	
}

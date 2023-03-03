package com.developer.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.service.HostUserService;
import com.developer.tutor.service.TutorService;
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("admin/*")
@RequiredArgsConstructor
public class AdminController {
	private final UsersService uService;
	private final TutorService tService;
	private final HostUserService hService;

	/**
	 * [Users] 튜터 미승인 목록을 출력한다.
	 * 
	 * @author SR
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "users/tutor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorUnapproveList() throws FindException {

		List<UsersDTO.unapproveTutorDTO> list = uService.selectAllUnapproveTutor();
		if (list.isEmpty()) {
			return new ResponseEntity<>("미승인한 튜터가 없습니다", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * [Tutor] 튜터로 승인한다.
	 * 
	 * @author SR
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "users/tutor/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorApply(@PathVariable String userId) throws FindException {

		tService.tutorApply(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * [Tutor] 튜터 승인거절
	 * 
	 * @author SR
	 * @param userId
	 * @return
	 * @throws RemoveException
	 */

	@DeleteMapping(value = "users/tutor/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorReject(@PathVariable String userId) throws RemoveException {

		tService.deleteTutor(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * [HostUser] 미승인 호스트 유저 목록 출력한다.
	 * 
	 * @author SR
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "host/unapprove")
	public ResponseEntity<?> hostUnapproveList() throws FindException {

		List<HostUserDTO.unApproveHostDTO> list = hService.hostUnapproveList();
		if (list.isEmpty()) {
			return new ResponseEntity<>("미승인 호스트 유저가 없습니다.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	/**
	 * [HostUser] 미승인 호스트를 승인한다.
	 * 
	 * @author SR
	 * @param hostId
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "host/unapprove/{hostId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> hostOk(@PathVariable String hostId) throws FindException {

		hService.readyOk(hostId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * [HostUser] 호스트 승인을 거절한다(삭제)
	 * 
	 * @author SR
	 * @param hostId
	 * @return
	 * @throws RemoveException
	 */
	@DeleteMapping(value = "host/unapprove/{hostId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> hostReject(@PathVariable String hostId) throws RemoveException {

		hService.deleteHost(hostId);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}

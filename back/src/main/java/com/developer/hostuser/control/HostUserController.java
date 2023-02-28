package com.developer.hostuser.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.service.HostUserService;

@RestController
@RequestMapping("hostuser/*")
public class HostUserController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private HostUserService hostUserService;

	/**
	 * 호스트유저 1개의 정보를 출력한다.
	 * 
	 * @author SR
	 * @param hostId
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> infoHost(String hostId, HttpSession session) throws FindException {

		hostId = "아이디1";
		// hostId = (String) session.getAttribute("logined");
		if (hostId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			HostUserDTO hostDTO = hostUserService.selectHost(hostId);
			return new ResponseEntity<>(hostDTO, HttpStatus.OK);
		}
	}

	/**
	 * 호스트 유저를 탈퇴한다. ready=2
	 * 
	 * @author SR
	 * @param hostId
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "out")
	public ResponseEntity<?> outHost(String hostId, HttpSession session) throws FindException {
		hostId="아이디2";
		// hostId = (String) session.getAttribute("logined");
		if (hostId == null) {
		
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			hostUserService.outHost(hostId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	/**
	 * 호스트 유저 정보를 수정한다.
	 * 
	 * @author SR
	 * @param hostId
	 * @param hostUserDTO
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PutMapping(value = "edit")
	public ResponseEntity<?> editHost(String hostId, @RequestBody HostUserDTO hostUserDTO, HttpSession session)
			throws FindException {
		hostId = "아이디4";
		// hostId = (String) session.getAttribute("logined");

		if (hostId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			//logger.error("컨트롤러값:" + hostUserDTO.getEmail());
			hostUserService.updateHost(hostId, hostUserDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	/**
	 * 미승인 호스트 유저 목록 출력한다.
	 * 
	 * @author SR
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "unapprovelist")
	public ResponseEntity<?> hostUnapproveList() throws FindException {
		List<HostUserDTO.unApproveHostDTO> list = hostUserService.hostUnapproveList();
		if (list.isEmpty()) {
			return new ResponseEntity<>("미승인 호스트 유저가 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@PatchMapping(value = "ok", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> hostOk(String hostId, HttpSession session) throws FindException {
		hostId = "아이디4";
		// hostId = (String) session.getAttribute("logined");

		if (hostId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			hostUserService.readyOk(hostId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	
	/**
	 * 호스트 승인을 거절한다(삭제)
	 * @author SR
	 * @param hostId
	 * @param session
	 * @return
	 * @throws RemoveException
	 */
	@DeleteMapping(value = "reject", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> hostReject(String hostId, HttpSession session) throws RemoveException {
		hostId = "hostdef";
		if (hostId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			hostUserService.deleteHost(hostId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}

package com.developer.roominfo.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.board.dto.BoardDTO;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roominfo.service.RoomInfoService;
import com.developer.studyroom.dto.StudyroomDTO;

@RestController
@RequestMapping("roominfo/*")
public class RoomInfoController {

	@Autowired
	private RoomInfoService riService;

	/**
	 * 룸 1개정보를 출력한다.
	 * 
	 * @author SR
	 * @param roomSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "{roomSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> infoRoom(@PathVariable long roomSeq) throws FindException {

		RoomInfoDTO roomDTO = riService.selectRoom(roomSeq);
		return new ResponseEntity<>(roomDTO, HttpStatus.OK);
	}

	/**
	 * 룸을 추가한다.
	 * 
	 * @author SR
	 * @param roomInfoDTO
	 * @param srSeq
	 * @return
	 * @throws AddException
	 */
	@PostMapping(value = "{srSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addRoom(@RequestBody RoomInfoDTO roomInfoDTO, @PathVariable long srSeq) {
		riService.insertRoom(roomInfoDTO, srSeq);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	/**
	 * 룸정보를 수정한다.
	 * 
	 * @author SR
	 * @param roomSeq
	 * @param roomInfoDTO
	 * @return
	 * @throws FindException
	 */
	@PutMapping(value = "edit/{roomSeq}")
	public ResponseEntity<?> editRoom(@PathVariable long roomSeq, @RequestBody RoomInfoDTO roomInfoDTO)
			throws FindException {
		riService.updateRoom(roomSeq, roomInfoDTO);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	/**
	 * 룸 1개를 삭제한다(status = 1)
	 * 
	 * @author SR
	 * @param roomSeq
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "{roomSeq}")
	public ResponseEntity<?> deleteRoom(@PathVariable long roomSeq) throws FindException {
		riService.deleteRoom(roomSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 방 목록을 출력한다.
	 * 
	 * @author SR
	 * @param srSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "list/{srSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listRoom(@PathVariable long srSeq) throws FindException {
		List<RoomInfoDTO.selectAllRoomDTO> list = riService.selectAllRoom(srSeq);
		if (list.isEmpty()) {
			return new ResponseEntity<>("추가한 방이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}
	
	/**
	 * 예약내역 출력
	 * @author choigeunhyeong
	 * @param srSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value="{srSeq}")
	public ResponseEntity<?> getReservation(@PathVariable Long srSeq) throws FindException{
		List<RoomInfoDTO.getReservationDTO> list = riService.getReservation(srSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	

	
}
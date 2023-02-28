package com.developer.reservation.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.reservation.service.ReservationService;
import com.developer.studyroom.dto.StudyroomDTO;

@RestController
@RequestMapping("reservation/*")
public class ReservationController {
	@Autowired
	private ReservationService reservationService;

	/**
	 * 해당 스터디카페의 모든 예약내역을 출력한다(목록)
	 * 
	 * @author SR
	 * @param hostId
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ListReservation(String hostId, HttpSession session) throws FindException {
		hostId = "아이디4";
		List<ReservationDTO.selectAllReservationDTO> list = reservationService.selectAllReservation(hostId);
		// hostId = (String) session.getAttribute("logined");

		if (hostId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else if (hostId != null &&list.isEmpty()) {
			return new ResponseEntity<>("예약내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	/**
	 * 예약 내역 1건을 출력한다.
	 * @author tpfks
	 * @param resSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "info/{resSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> InfoReservation(@PathVariable long resSeq) throws FindException {
		List<ReservationDTO.selectAllReservationDTO>list = reservationService.infoReservation(resSeq);
		
		if(list.isEmpty()) {
			return new ResponseEntity<>("예약 내역이 없습니다", HttpStatus.BAD_REQUEST);
		}
			return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * 예약내역 1건을 삭제한다.
	 * @author SR
	 * @param resSeq
	 * @param session
	 * @return
	 * @throws RemoveException
	 */
	@DeleteMapping(value = "{resSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ListReservation(@PathVariable long resSeq, HttpSession session) throws RemoveException {
		reservationService.deleteReservation(resSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

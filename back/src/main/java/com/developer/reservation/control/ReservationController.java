package com.developer.reservation.control;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.reservation.service.ReservationService;

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
	/**
	 * [스터디카페 예약페이지] 예약정보를 예약테이블에 넣어 예약내역에 insert
	 * @author ds
	 * @throws 전체정보 출력시  AddException예외발생한다
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reserve(@RequestBody ReservationDTO.insertRvDTO rDTO)throws AddException{
		
		reservationService.insertRv(rDTO);
		return new ResponseEntity<>(rDTO,HttpStatus.OK);
	}
	
	/**
	 * [마이페이지 스터디카페 후기페이지] 아이디값으로 후기를 작성하지 않은 예약리스트를 출력한다
	 * @author ds
	 * @param userId
	 * @return List<Object[]> 유저의 작성한 이용후기 리스트
	 */
	@GetMapping(value="get/{userId}")//, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<?> getRqRmRv(@PathVariable String userId) throws FindException{
		List<ReservationDTO.selectRmRvDTO> list = reservationService.selectMyReqRmRv(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * [스터디카페 예약페이지] 룸 시퀀스와 예약일을 받아 이미 예약된 예약정보에 대한 리스트를 출력한다
	 * @author ds
	 * @param roomSeq 스터디룸 시퀀스
	 * @param usingDate 예약일 
	 * @return List<Object[]> 특정일자의 해당 스터디룸 예약정보
	 * @throws 전체정보 출력시  FindException, ParseException예외발생한다
	 */
	
	@GetMapping(value = "reserve/{roomSeq}")
	public ResponseEntity<?> getReservablity(@PathVariable Long roomSeq, @RequestParam String usingDate) throws FindException, ParseException{
		try {
			
			List<ReservationDTO.selectAllByUsingDateDTO> list = reservationService.selectAllByUsingDate(roomSeq, usingDate);
			return new ResponseEntity<>(list,HttpStatus.OK);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return new ResponseEntity<>("예약이 모두 마감되었습니다.",HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * [마이페이지 - 스터디카페 예약내역 페이지] 아이디값을 받아와 전체 예약내역을 최신순으로 출력한다
	 * @author ds
	 * @param userId 유저 아이디 
	 * @return List<ReservationDTO.selectMyResHistoryDTO> 유저의 전체 예약 내역(최신순) 
	 * @throws 전체정보 출력시  FindException예외발생한다
	 */
	@GetMapping(value = "get",produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<?> getMyResHistoery(@RequestBody String userId)throws FindException{
		List<ReservationDTO.selectMyResHistoryDTO> list = reservationService.selectMyResHistory(userId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}

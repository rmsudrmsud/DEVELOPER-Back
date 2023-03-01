package com.developer.roomreview.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.roomreview.dto.RoomReviewDTO;
import com.developer.roomreview.dto.RoomReviewDTO.RoomReviewInsertDTO;
import com.developer.roomreview.service.RoomReviewService;

@RestController
@RequestMapping("roomreview/*")
public class RoomReviewController {
	@Autowired
	private RoomReviewService rrservice;
	
	/**
	 * [마이페이지 스터디카페 후기페이지] 후기작성한다
	 * @author ds
	 * @param resSeq, content, star
	 */
	@PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addRoomReview(@RequestBody RoomReviewInsertDTO rrDTO) throws AddException{
		rrservice.insertReview(rrDTO);
		return new ResponseEntity<>(rrDTO,HttpStatus.OK);
	}
	/**포스트맨성공 
	 *  [마이페이지 스터디카페 후기페이지]특정 스터디룸 후기 리스트 전체출력
	 * @author ds
	 * @param srSeq 스터디카페 시퀀스
	 */
	@GetMapping(value="get/{srSeq}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllBySrSeq(@PathVariable Long srSeq) throws FindException{
		List<RoomReviewDTO.RoomReviewSelectAllDTO> list = rrservice.selectAll(srSeq);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	/**
	 * [마이페이지 스터디카페 후기페이지] 유저 아이디로 작성한 이용후기 목록을 출력한다
	 * @author ds
	 * @param userId 유저아이디 
	 * @return roomReviewDTO 유저의 작성한 이용후기 전체목록
	 */
	@GetMapping(value= "myreview/{userId}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> getReviewByUserid(@PathVariable String userId) throws FindException{
		List<RoomReviewDTO.selectMyRmRvDTO> list = rrservice.selectMyRmRv(userId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	/**
	 * [마이페이지 스터디카페 후기페이지] 예약시퀀스를 받아 해당 후기상세출력한다
	 * @author ds
	 * @param resSeq 예약 시퀀스
	 * @return RoomReview 유저의 작성한 이용후기 상세정보
	 */
	@GetMapping(value = "detail/{resSeq}",produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> getReviewDetailByResSeq(@PathVariable Long resSeq) throws FindException{
		List<RoomReviewDTO.selectMyRmRvDetailDTO> dto = rrservice.selectRmRvDetail(resSeq);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	
	


	
	
	
}

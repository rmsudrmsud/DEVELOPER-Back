package com.developer.roominfo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roominfo.service.RoomInfoService;

@RestController
@RequestMapping("roominfo/*")
public class RoomInfoController {
	@Autowired
	private RoomInfoService roomservice;
	
	/**
	 * 예약내역 출력
	 * @author choigeunhyeong
	 * @param srSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value="{srSeq}")
	public ResponseEntity<?> getReservation(@PathVariable Long srSeq) throws FindException{
		List<RoomInfoDTO.getReservationDTO> list = roomservice.getReservation(srSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}

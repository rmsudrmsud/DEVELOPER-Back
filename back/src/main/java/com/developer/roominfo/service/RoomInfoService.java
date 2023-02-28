package com.developer.roominfo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roominfo.repository.RoomInfoRepository;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.users.dto.UsersDTO;

@Service
public class RoomInfoService {
	@Autowired
	RoomInfoRepository roomRepository;
	
	/**
	 * 스터디룸번호로 해당하는 예약내역 출력
	 * @author choigeunhyeong
	 * @param srSeq
	 * @return
	 * @throws FindException
	 */
	public List<RoomInfoDTO.getReservationDTO> getReservation(Long srSeq) throws FindException{
		List<Object[]> RList = roomRepository.getReservation(srSeq);
		 List<RoomInfoDTO.getReservationDTO> dto = new ArrayList<>();
		 for(int i = 0; i < RList.size(); i++) {
			 RoomInfoDTO.getReservationDTO roomInfoDTO = new RoomInfoDTO.getReservationDTO();
			 ReservationDTO.getReservationDTO resDTO = new  ReservationDTO.getReservationDTO();
			 BigDecimal res_seq = (BigDecimal) RList.get(i)[0];
			 Long resultres_seq = res_seq.longValue();
			 resDTO.setResSeq(resultres_seq);
			 resDTO.setUsingDate((Date) RList.get(i)[4]);
			 resDTO.setStartTime((String) RList.get(i)[5]);
			 resDTO.setEndTime((String) RList.get(i)[6]);
			 UsersDTO.UsersNameDTO uDTO = new UsersDTO.UsersNameDTO();
			 uDTO.setUserId((String) RList.get(i)[2]);
			 uDTO.setNickname((String) RList.get(i)[3]);
			 roomInfoDTO.setName((String) RList.get(i)[1]);
			 roomInfoDTO.setReservation(resDTO);
			 roomInfoDTO.setUsers(uDTO);
			 dto.add(roomInfoDTO);
		 }
		 return dto;
	}
}

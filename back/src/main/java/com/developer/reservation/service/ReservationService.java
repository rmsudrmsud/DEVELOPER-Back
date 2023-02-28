package com.developer.reservation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.hostuser.repository.HostUserRepository;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.reservation.entity.Reservation;
import com.developer.reservation.repository.ReservationRepository;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roominfo.repository.RoomInfoRepository;
import com.developer.users.dto.UsersDTO;
import com.developer.users.repository.UsersRepository;

@Service
public class ReservationService {
	@Autowired
	ReservationRepository rRepository;
	@Autowired
	private UsersRepository uRepository;
	@Autowired
	private RoomInfoRepository roomRepository;
	@Autowired
	private HostUserRepository hRepository;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 해당 스터디카페의 모든 예약내역을 출력한다(목록)
	 * @author SR
	 * @param hostId
	 * @return
	 * @throws FindException
	 */
	public List<ReservationDTO.selectAllReservationDTO> selectAllReservation(String hostId) throws FindException {
		List<Object[]> rList = rRepository.selectAllReservation(hostId);
		System.err.println("값"+rList.size());
		List<ReservationDTO.selectAllReservationDTO> rListDto = new ArrayList<>();
		for (int i = 0; i < rList.size(); i++) {
			ReservationDTO.selectAllReservationDTO rDto = new ReservationDTO.selectAllReservationDTO();
			BigDecimal resSeq = (BigDecimal) rList.get(i)[0];
			long convertResSeq = resSeq.longValue();
			rDto.setResSeq(convertResSeq);
			rDto.setUserId((String) rList.get(i)[2]);
			rDto.setHostId((String) rList.get(i)[4]);

			//logger.error("값"+rList.get(i)[5].getClass().getName());
			rDto.setUsingDate((Date) rList.get(i)[5]);
			rDto.setStartTime((String) rList.get(i)[6]);
			rDto.setEndTime((String) rList.get(i)[7]);
			RoomInfoDTO.selectAllReservationDTO roomDto = new RoomInfoDTO.selectAllReservationDTO();
			roomDto.setName((String) rList.get(i)[1]);

			UsersDTO.selectAllReservationDTO uDto = new UsersDTO.selectAllReservationDTO();
			uDto.setName((String) rList.get(i)[3]);

			rDto.setUsersDTO(uDto);
			rDto.setRoomInfoDTO(roomDto);

			rListDto.add(rDto);
		}
		return rListDto;
	}

	/**
	 * 예약내역 1건을 출력한다.
	 * @author SR
	 * @param resSeq
	 * @return
	 * @throws FindException
	 */
	public List<ReservationDTO.selectAllReservationDTO> infoReservation(long resSeq) throws FindException {
		//Optional<ReservationDTO.selectAllReservationDTO> optR= rRepository.selectReservation(resSeq);
		List<Object[]> rList = rRepository.selectReservation(resSeq);
		List<ReservationDTO.selectAllReservationDTO> rListDto = new ArrayList<>();
			
		for(int i=0; i<rList.size(); i++) {
			ReservationDTO.selectAllReservationDTO rDto = new ReservationDTO.selectAllReservationDTO();
			BigDecimal resSeq1 = (BigDecimal) rList.get(i)[0];
			long convertSeq = resSeq1.longValue();
			rDto.setResSeq(convertSeq);
			rDto.setUserId((String)rList.get(i)[1]);
			rDto.setUsingDate((Date)rList.get(i)[5]);
			rDto.setStartTime((String)rList.get(i)[6]);
			rDto.setEndTime((String)rList.get(i)[7]);
			rDto.setHostId((String)rList.get(i)[8]);
			
			UsersDTO.selectAllReservationDTO uDto = new UsersDTO.selectAllReservationDTO();
			uDto.setName((String)rList.get(i)[2]);
			uDto.setTel((String)rList.get(i)[3]);
			
			RoomInfoDTO.selectAllReservationDTO riDto = new RoomInfoDTO.selectAllReservationDTO();
			riDto.setName((String)rList.get(i)[4]);
			
			rDto.setUsersDTO(uDto);
			rDto.setRoomInfoDTO(riDto);
			
			rListDto.add(rDto);
		}
		return rListDto;
	}
	 
	/**
	 * 예약내역 1건을 삭제한다.
	 * @author SR
	 * @param resSeq
	 * @throws RemoveException
	 */
	public void deleteReservation(long resSeq) throws RemoveException {
		 Optional<Reservation> optR = rRepository.findById(resSeq);
		if (optR.isPresent()) {
			Reservation entityR = optR.get();
			rRepository.delete(entityR);
		} else {
			throw new RemoveException("잘못된 예약번호입니다.");
		}
	}

}

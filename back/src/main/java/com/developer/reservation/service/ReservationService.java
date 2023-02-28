package com.developer.reservation.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.repository.HostUserRepository;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.reservation.entity.Reservation;
import com.developer.reservation.repository.ReservationRepository;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roominfo.repository.RoomInfoRepository;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class ReservationService {
	@Autowired
	ReservationRepository rRepository;
	private UsersRepository uRepository;
	@Autowired
	private RoomInfoRepository roomRepository;
	
	@Autowired
	private HostUserRepository hRepository;
	
	//예약하기
	public void insertRv(String userId, String hostId, Long roomSeq, String usingDate, String startTime, String endTime) throws FindException {
		Reservation r = new Reservation();
		Optional<Users> optU = uRepository.findById(userId);
		Users u = optU.get();
		//r.setUserId(u);
		Optional<HostUser> optH = hRepository.findById(hostId);
		HostUser hu= optH.get();
		r.setHostUser(hu);
		Optional<RoomInfo> optR= roomRepository.findById(roomSeq);
		RoomInfo ri = optR.get();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try {
			date = format.parse(usingDate);
		//	r.setUsingDate(date);
			r.setRoominfo(ri);
			r.setStartTime(startTime);
			r.setEndTime(endTime);
			rRepository.save(r);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	//예약시퀀스로 예약상세 검색
	public Reservation selectReservation(Long resSeq)throws FindException{
		Optional<Reservation> optR=rRepository.findById(resSeq);
		
		return optR.get();
		
	}
	
	//특정날짜에 예약가능한지 확인(룸시퀀스와 예약날짜)
	public List<Object[]> selectAllByUsingDate(Long roomSeq, String usingDate) throws FindException, ParseException{
		 SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
		 Date date=formatter.parse(usingDate);
		List<Object[]> list= rRepository.findAllByUsingDate(roomSeq, date);
		return list;
	}
	
	//유저아이디로 예약내역 찾기
	public List<Object[]> selectMyResHistory(String userId)throws FindException{
		List<Object[]> list = rRepository.findByUserId(userId);
		return list;
	}
	
	//예약삭제
	public void deleteRv(Long resSeq)throws FindException {
		rRepository.deleteById(resSeq);
	}
	
	//호스트 아이디로 해당 스터디카페의 예약내역 전체출력
	public Reservation[] selectByHostId(String hostId)throws FindException{
		Optional<HostUser> optH = hRepository.findById(hostId);
		HostUser hu=optH.get();
		Reservation[] r = rRepository.findByhostUser(hu.getHostId());
		return r;
	}
	
}

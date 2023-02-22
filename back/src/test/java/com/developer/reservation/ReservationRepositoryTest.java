package com.developer.reservation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.repository.HostUserRepository;
import com.developer.reservation.entity.Reservation;
import com.developer.reservation.repository.ReservationRepository;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roominfo.repository.RoomInfoRepository;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;
@SpringBootTest
class ReservationRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private ReservationRepository res;
	@Autowired
	private UsersRepository ur;
	@Autowired
	private RoomInfoRepository rir;
	
	@Autowired
	private HostUserRepository hos;
	
	
	
	@Test//됨
	@DisplayName("예약add 기능 테스트")
	
	void testReservationAdd() {
		Reservation r = new Reservation();
		Optional<Users> optU = ur.findById("aaa");
		Users u = optU.get();
		r.setUserId(u);
	   Optional<HostUser> optH = hos.findById("suho522");
		HostUser hu= optH.get();
		r.setHostUser(hu);
		Optional<RoomInfo> optR= rir.findById(1L);
		RoomInfo ri = optR.get();
		Date date = new Date("2023/03/12");
		r.setUsingDate(date);
		r.setRoomInfo(ri);
		r.setStartTime("15:00");
		r.setEndTime("16:00");
		res.save(r);
		
	}
	
	@Test//됨
	@DisplayName("예약FindbyId 기능 테스트") //시퀀스로 예약내역 검색
	void testReservationFindById() {
		Optional<Reservation> optR=res.findById(1L);
		
		assertTrue(optR.isPresent());
		Reservation r =optR.get();
		
		
	}
	@Test//됨
	@DisplayName("룸시퀀스와 예약날짜로 예약내역 확인 기능 테스트")
	void testSelectAllByUsingDate() throws ParseException {
		 SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
		 
	        // 문자열 -> Date
	        Date usingdate = formatter.parse("23/03/12");
		List<Object> reservation = res.findAllByUsingDate(1L, usingdate);
		

			logger.info(reservation.getClass().toString()+"test");
	
	
	}
	
	@Test //(안됨 값이 안나옴)
	@DisplayName("유저아이디로 예약내역 찾기 기능 테스트")
	void testSelectByUserId() {
		
	Iterable<Reservation> ir=res.findByUserId("suho522");
	ir.forEach(r ->{
		logger.info("아이디: "+r.getUserId()+"예약일: "+r.getUsingDate()+"예약한 방: "+r.getRoomInfo().getName()+
				"스터디카페명"+r.getStudyroom().getName()+"시작시간"+r.getStartTime()+"종료시간"+r.getEndTime());
	});
	}
	
	

	@Test//잘됨
	@DisplayName("전체예약내역확인기능 테스트")
	void testFindAll() {
		Iterable<Reservation> rv = res.findAll();
		rv.forEach(r ->{
			logger.info("예약번호:"+r.getResSeq()+"유저아이디"+r.getUserId()+"룸시퀀스:"//+r.getRoomInfo().getRoomSeq()
					+"시작시간:"+r.getStartTime()+"종료시간:"+r.getEndTime()+"예약일"+r.getUsingDate()
			+"호스트ID"+r.getHostUser().getHostId());
			
		});
	}
	
	@Test //성공
	@DisplayName("예약시퀀스로 예약상세 찾기 테스트")
	void testFindDetail() {
		Reservation r = res.findByResSeq(1L);
		logger.info("예약번호:"+r.getResSeq()+"유저아이디"+r.getUserId()+"룸시퀀스:"//+r.getRoomInfo().getRoomSeq()
				+"시작시간:"+r.getStartTime()+"종료시간:"+r.getEndTime()+"예약일"+r.getUsingDate()
		+"호스트ID"+r.getHostUser().getHostId());
	}

	@Test //
	@DisplayName("예약시퀀스Delete 기능 테스트")
	void testReservationDelete() {
	res.deleteByResSeq(5L);;
		
	}
	@Test
	void testFindByResSeq() {
		Optional<Reservation> optR =res.findById(1L);
		Reservation r=optR.get();
		
		r.getResSeq();
		
	}

	@Test
	void testFindByHostId() {
	Optional<HostUser> optH	=hos.findById("suho522");
	HostUser hu=optH.get();
	Reservation r = res.findByhostUser("suho522");
		
		logger.info("예약번호:"+r.getResSeq()+"유저아이디"+r.getUserId()+"룸시퀀스:"//+r.getRoomInfo().getRoomSeq()
				+"시작시간:"+r.getStartTime()+"종료시간:"+r.getEndTime()+"예약일"+r.getUsingDate()
		+"호스트ID"+r.getHostUser().getHostId());
		
	
		
	}
}

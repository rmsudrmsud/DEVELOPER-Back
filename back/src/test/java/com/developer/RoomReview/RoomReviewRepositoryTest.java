package com.developer.RoomReview;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.reservation.entity.Reservation;
import com.developer.reservation.repository.ReservationRepository;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roomreview.entity.RoomReview;
import com.developer.roomreview.repository.RoomReviewRepository;
@SpringBootTest
class RoomReviewRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private RoomReviewRepository rrr;
	
	@Autowired
	private ReservationRepository resr;
	
	@Test
	void test1() {
		rrr.findAll();
	}
	@DisplayName("리뷰add 기능 테스트")
	@Test
	void SaveRoomReviewAdd() {
		RoomReview rr = new RoomReview();
	
		Optional<Reservation> optR =resr.findById(1L);
		Reservation r=optR.get();
		rr.setResSeq(r.getResSeq());
		rr.setContent("스터디룸 좋아요");
		rr.setStar(1);
		rrr.save(rr);
	}
	
	@Test
	@DisplayName("리뷰FindById 기능 테스트")
	void testRoomReviewFindById() {
		Optional<RoomReview> optReview = rrr.findById(1L);
		assertTrue(optReview.isPresent());
		RoomReview rr = optReview.get();
		
	}
	
	@Test
	@DisplayName("전체리뷰내역확인기능 테스트")
	void testFindAll() {
		Iterable<RoomReview> rr = rrr.findAll();
		rr.forEach(r->{
			logger.info("리뷰번호: "+"리뷰내용: "+r.getContent()+"별점: "+r.getStar()
			+"작성일"+r.getCDate());
		});
	}
	
	@Test
	@DisplayName("리뷰Delete 기능 테스트")
	void testRoomReviewDelete() {
		Optional<RoomReview> optR= rrr.findById(2L);
		RoomReview rr = optR.get();
		rrr.delete(rr);
	}
	
	@Test
	@DisplayName("srSeq로 특정 스터디룸 후기 리스트 전체출력 테스트")
	void testfindRoomReviewAll() {
		//rrr.findRoomReviewAll(1L);
	}

}

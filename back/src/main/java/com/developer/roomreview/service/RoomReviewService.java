package com.developer.roomreview.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.reservation.entity.Reservation;
import com.developer.reservation.repository.ReservationRepository;
import com.developer.roomreview.entity.RoomReview;
import com.developer.roomreview.repository.RoomReviewRepository;

@Service
public class RoomReviewService {
	@Autowired
	private RoomReviewRepository rrr;
	
	@Autowired
	private ReservationRepository resr;
	
	
	//후기작성
	public void insertRoomReview(Long resSeq, String content, Integer star) throws FindException{
		RoomReview rr = new RoomReview();
		
		Optional<Reservation> optR =resr.findById(resSeq);
		Reservation r=optR.get();
		rr.setResSeq(r.getResSeq());
		rr.setContent(content);
		rr.setStar(star);
		
		rrr.save(rr);
	}
	
	//후기삭제
	public void deleteRoomReview(Long resSeq) throws FindException{
		rrr.deleteById(resSeq);
		
	}

	//특정 스터디룸 후기 리스트 전체출력
	public List<Object[]> selectAll(Long srSeq) throws FindException{
		return null;
	}

	
}

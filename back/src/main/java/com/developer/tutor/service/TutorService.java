package com.developer.tutor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.reservation.entity.Reservation;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;

@Service
public class TutorService {
	@Autowired
	private TutorRepository tRepository;
	
	//[JW] 튜터등록 
	public void addTutor(Tutor tutor) throws FindException{
		tRepository.save(tutor);
	}
	
	/**
	 * 튜터승인거절
	 * @author SR
	 * @param userId
	 * @throws RemoveException
	 */
	public void deleteTutor(String userId) throws RemoveException{
		Optional<Tutor> optT = tRepository.findById(userId);
		if (optT.isPresent()) {
			Tutor entityT = optT.get();
			tRepository.delete(entityT);
		} else {
			throw new RemoveException("해당 유저가 존재하지 않습니다.");
		}	
	}
}

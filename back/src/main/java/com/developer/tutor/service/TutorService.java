package com.developer.tutor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
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
}

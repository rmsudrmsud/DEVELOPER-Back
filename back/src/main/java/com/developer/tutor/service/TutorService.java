package com.developer.tutor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.tutor.dto.TutorDTO;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class TutorService {
	@Autowired
	private TutorRepository tRepository;
	@Autowired
	private UsersRepository uRepository;
	
	//[JW] 튜터등록 및 수정
	public void saveTutor(TutorDTO.saveTutorDTO tDTO) throws FindException{
		Optional<Tutor> t = tRepository.findById(tDTO.getTutorId());
		Tutor tEntity = new Tutor();
		
		if(!t.isPresent()) {
			tEntity.setTutorId(tDTO.getTutorId());
			tEntity.setInfo(tDTO.getInfo());
			tEntity.setImgPath(tDTO.getImgPath());
			tEntity.setStarAvg(0.0);
			tEntity.setApplyOk(0);
			Optional<Users> u = uRepository.findById(tDTO.getTutorId());
			tEntity.setUsers(u.get());
		} else {
			tEntity = t.get();
			tEntity.setInfo(tDTO.getInfo());
			tEntity.setImgPath(tDTO.getImgPath());
		}
		tRepository.save(tEntity);
	}
	
}

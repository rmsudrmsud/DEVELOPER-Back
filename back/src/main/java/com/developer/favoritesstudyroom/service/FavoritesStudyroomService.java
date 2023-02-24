package com.developer.favoritesstudyroom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.favoritesstudyroom.entity.FavoritesStudyroom;
import com.developer.favoritesstudyroom.repository.FavoritesStudyroomRepository;
import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.repository.StudyroomRepository;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class FavoritesStudyroomService {
	@Autowired
	private FavoritesStudyroomRepository fsr;
	
	@Autowired
	private StudyroomRepository sr;
	
	@Autowired
	private UsersRepository user;
	
	//즐겨찾기 추가
	public void insert(Long srSeq, String userId)throws FindException{
		FavoritesStudyroom fs = new FavoritesStudyroom();
		Optional<Studyroom> optS = sr.findById(srSeq);
		Studyroom s=optS.get();
		fs.setStudyroom(s);
		Optional<Users> optU = user.findById(userId);
		Users u=optU.get();
		fs.setUserId(u);
		fsr.save(fs);
		
	}
	
	//즐겨찾기 삭제
	public void delete(Long favSeq)throws FindException{
		fsr.deleteById(favSeq);
	}
}

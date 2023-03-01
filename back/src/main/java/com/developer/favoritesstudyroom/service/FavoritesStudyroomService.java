package com.developer.favoritesstudyroom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.favoritesstudyroom.dto.FavoritesStudyroomDTO;
import com.developer.favoritesstudyroom.entity.FavoritesStudyroom;
import com.developer.favoritesstudyroom.repository.FavoritesStudyroomRepository;
import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.repository.StudyroomRepository;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class FavoritesStudyroomService {
	@Autowired
	private FavoritesStudyroomRepository fsRepository;
	
	@Autowired
	private StudyroomRepository sRepository;
	
	@Autowired
	private UsersRepository uRepository;
	
	 /**
	    * [스터디카페 상세페이지]즐겨찾기 추가기능
	    * @author ds
	    * @param fvDTO
	    * @throws AddException
	    */
	   
	   public void insertFVstudyroom(FavoritesStudyroomDTO.fvInsertDTO fvDTO)throws AddException{
	      FavoritesStudyroom fs = new FavoritesStudyroom ();
	      Optional<Users> optU= uRepository.findById(fvDTO.getUserId());
	      Users u = optU.get();
	      fs.setUsers(u);
	      Optional<Studyroom> optS = sRepository.findById(fvDTO.getSrSeq());
	      Studyroom s = optS.get();
	      fs.setStudyroom(s);
	      fsRepository.save(fs);
	   }
}

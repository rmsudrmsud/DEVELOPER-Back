package com.developer.favoritesstudyroom.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.AddException;
import com.developer.exception.RemoveException;
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
	   
	   public void insertFVstudyroom(Long srSeq , String logined)throws AddException{
	      FavoritesStudyroom fs = new FavoritesStudyroom ();
	      Optional<Users> optU= uRepository.findById(logined);
	      Users u = optU.get();
	      fs.setUsers(u);
	      Optional<Studyroom> optS = sRepository.findById(srSeq);
	      Studyroom s = optS.get();
	      fs.setStudyroom(s);
	      fsRepository.save(fs);
	   }
	   
	   public void deleteFvstudyroom(Long favSeq) throws RemoveException{
		 Optional<FavoritesStudyroom> optF=fsRepository.findById(favSeq);
		 if(optF.isPresent()) {
			 fsRepository.deleteById(favSeq);
		 }else {
			 throw new RemoveException("해당 시퀀스가 없습니다.");
		 }
	   }
}

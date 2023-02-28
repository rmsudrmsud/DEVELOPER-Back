package com.developer.tutor.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.tutor.dto.TutorDTO;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TutorService {
	
	private final TutorRepository tRepository;
	private final UsersRepository uRepository;
	private final ModelMapper modelMapper;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 튜터 등록 및 수정
	 * @author moonone
	 * @param tDTO
	 * @throws FindException
	 */
	public void saveTutor(TutorDTO.saveTutorDTO tDTO) throws FindException{
		Optional<Tutor> t = tRepository.findById(tDTO.getTutorId());
		Optional<Users> u = uRepository.findById(tDTO.getTutorId());		
		Tutor tEntity = new Tutor();

		if(!t.isPresent()) {
			tDTO.setApplyOk(0);
			tDTO.setStarAvg(0.0);
			tDTO.setUsers(u.get());
		}
		modelMapper.map(tDTO, tEntity);
		tRepository.save(tEntity);
	}
	
	
	/**
	 * 튜터가 생성한 클래스 목록 + 튜터 정보
	 * @author moonone
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	public  List<TutorDTO.selectTutorDetailDTO> selectTutorDetail(String tutorId) throws FindException{
		List<Object[]> list = tRepository.selectTutorDetail(tutorId);
		Optional<Users> u = uRepository.findById(tutorId);		
		logger.error("값1 " + u.get().getName());
		
		List<TutorDTO.selectTutorDetailDTO> tResult = new ArrayList<>();	
		List<LessonDTO.onlyLessonDTO> lResult = new ArrayList<>();
		
			TutorDTO.selectTutorDetailDTO tDTO = new TutorDTO.selectTutorDetailDTO();		
			tDTO.setInfo((String)list.get(0)[12]);
			tDTO.setImgPath((String)list.get(0)[13]);
			tDTO.setStarAvg(((BigDecimal)list.get(0)[14]).doubleValue());
			tDTO.setName(u.get().getName());
	
			for(int i=0; i<list.size(); i++) {	
				LessonDTO.onlyLessonDTO lDTO = new LessonDTO.onlyLessonDTO();
				lDTO.setLessonSeq(((BigDecimal)list.get(i)[0]).longValue());
				lDTO.setLessonName((String)list.get(i)[1]);
				lDTO.setCategory(((BigDecimal)list.get(i)[2]).intValue());
				lDTO.setContent((String)list.get(i)[3]);
				lDTO.setPeople(((BigDecimal)list.get(i)[4]).intValue());
				lDTO.setImgPath((String)list.get(i)[5]);
				lDTO.setStartCdate((LocalDate) (list.get(i)[6]));
				lDTO.setEndCdate(((LocalDate)list.get(i)[7]));
				lDTO.setPrice(((BigDecimal)list.get(i)[8]).intValue());
				lDTO.setStartDate(((LocalDate)list.get(i)[9]));
				lDTO.setEndDate(((LocalDate)list.get(i)[10]));
				lDTO.setImgPath((String)list.get(i)[11]);
				
				lResult.add(lDTO);
			}
			tDTO.setLesson(lResult);
			
			logger.error("값 " + tDTO.getLesson().toString() + ", " + tDTO.getName() + ", " + tDTO.getInfo());
			tResult.add(tDTO);
			return tResult;	
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

package com.developer.lesson.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;
import com.developer.tutor.dto.TutorDTO;
import com.developer.users.dto.UsersDTO;


@Service
public class LessonService {
	
	@Autowired
	private LessonRepository lRepository;
	

//	@Autowired
//    private ModelMapper modelMapper;
    
	/**
	 * 튜터아이디에 해당하는 진행예정 클래스 리스트 출력
	 * @author Jin
	 * @param tutorId
	 * @return LessonDTO List형태로 반환
	 * @throws FindException
	 */
    public List<LessonDTO.GetLessonByUser> getLessonByUser1(String tutorId) throws FindException{
    	List<Object[]> Llist = lRepository.getLessonByUser1(tutorId);
    	List<LessonDTO.GetLessonByUser> dto = new ArrayList<>();
    	for(int i=0; i<Llist.size(); i++) {
    		TutorDTO tDTO = new TutorDTO();
    		LessonDTO.GetLessonByUser lDTO = new LessonDTO.GetLessonByUser();
    		UsersDTO uDTO = new UsersDTO();
    		lDTO.setLessonName((String)Llist.get(i)[0]);
    		tDTO.setTutorId((String)tutorId);
    		uDTO.setUserId((String)tutorId);
    		tDTO.setUdto(uDTO);
    		lDTO.setTDTO(tDTO);
    		dto.add(lDTO);
    		
    	}
    	return dto;
    }
    
	/**
	 * 튜터아이디에 해당하는 진행중인 클래스 리스트 출력
	 * @author Jin
	 * @param tutorId
	 * @return LessonDTO List형태로 반환
	 * @throws FindException
	 */
    public List<LessonDTO.GetLessonByUser> getLessonByUser2(String tutorId) throws FindException{
    	List<Object[]> Llist = lRepository.getLessonByUser2(tutorId);
    	List<LessonDTO.GetLessonByUser> dto = new ArrayList<>();
    	for(int i=0; i<Llist.size(); i++) {
    		TutorDTO tDTO = new TutorDTO();
    		LessonDTO.GetLessonByUser lDTO = new LessonDTO.GetLessonByUser();
    		UsersDTO uDTO = new UsersDTO();
    		lDTO.setLessonName((String)Llist.get(i)[0]);
    		tDTO.setTutorId((String)tutorId);
    		uDTO.setUserId((String)tutorId);
    		tDTO.setUdto(uDTO);
    		lDTO.setTDTO(tDTO);
    		dto.add(lDTO);
    		
    	}
    	return dto;
    }
    
	/**
	 * 튜터아이디에 해당하는 진행완료 클래스 리스트 출력
	 * @author Jin
	 * @param tutorId
	 * @return LessonDTO List형태로 반환
	 * @throws FindException
	 */
    public List<LessonDTO.GetLessonByUser> getLessonByUser3(String tutorId) throws FindException{
    	List<Object[]> Llist = lRepository.getLessonByUser3(tutorId);
    	List<LessonDTO.GetLessonByUser> dto = new ArrayList<>();
    	for(int i=0; i<Llist.size(); i++) {
    		TutorDTO tDTO = new TutorDTO();
    		LessonDTO.GetLessonByUser lDTO = new LessonDTO.GetLessonByUser();
    		UsersDTO uDTO = new UsersDTO();
    		lDTO.setLessonName((String)Llist.get(i)[0]);
    		tDTO.setTutorId((String)tutorId);
    		uDTO.setUserId((String)tutorId);
    		tDTO.setUdto(uDTO);
    		lDTO.setTDTO(tDTO);
    		dto.add(lDTO);
    		
    	}
    	return dto;
    }
    
    /**
     * 승인 대기중인 클래스(튜티기준)
     * @author Jin
     * @param userId
     * @return
     * @throws FindException
     */
	public List<LessonDTO.applyLessonBytutee> getApplyLesson(String userId) throws FindException{
		List<Object[]> Llist = lRepository.getApplyLesson(userId);
		List<LessonDTO.applyLessonBytutee> dto = new ArrayList<>();
		for(int i=0; i<Llist.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			LessonDTO.applyLessonBytutee lDTO = new LessonDTO.applyLessonBytutee();
			AppliedLessonDTO.selectAppliedLessonDTO aDTO = new AppliedLessonDTO.selectAppliedLessonDTO();
			uDTO.setUserId((String)userId);
			lDTO.setLessonName((String)Llist.get(i)[0]);
			aDTO.setUsersDTO(uDTO);
			lDTO.setAlDTO(aDTO);
			dto.add(lDTO);
		}
		return dto;
	}
	
	/**
	 * 진행예정 클래스(튜티기중)
	 * @author Jin
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	public List<LessonDTO.applyLessonBytutee> upComingLesson(String userId) throws FindException{
		List<Object[]> Llist = lRepository.upComingLesson(userId);
		List<LessonDTO.applyLessonBytutee> dto = new ArrayList<>();
		for(int i=0; i<Llist.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			LessonDTO.applyLessonBytutee lDTO = new LessonDTO.applyLessonBytutee();
			AppliedLessonDTO.selectAppliedLessonDTO aDTO = new AppliedLessonDTO.selectAppliedLessonDTO();
			uDTO.setUserId((String)userId);
			lDTO.setLessonName((String)Llist.get(i)[0]);
			aDTO.setUsersDTO(uDTO);
			lDTO.setAlDTO(aDTO);
			dto.add(lDTO);
		}
		return dto;
	}
	
	
	/**
	 * 진행중인 클래스(튜티기준)
	 * @author Jin
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	public List<LessonDTO.applyLessonBytutee> onGoingLesson(String userId) throws FindException{
		List<Object[]> Llist = lRepository.onGoingLesson(userId);
		List<LessonDTO.applyLessonBytutee> dto = new ArrayList<>();
		for(int i=0; i<Llist.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			LessonDTO.applyLessonBytutee lDTO = new LessonDTO.applyLessonBytutee();
			AppliedLessonDTO.selectAppliedLessonDTO aDTO = new AppliedLessonDTO.selectAppliedLessonDTO();
			uDTO.setUserId((String)userId);
			lDTO.setLessonName((String)Llist.get(i)[0]);
			aDTO.setUsersDTO(uDTO);
			lDTO.setAlDTO(aDTO);
			dto.add(lDTO);
		}
		return dto;
	}
	
	
    
    /**
     * 레슨번호에 해당하는 클래스 상세정보 출력
     * @author Jin
     * @param lessonSeq
     * @return
     * @throws FindException
     */
    public List<LessonDTO.selectDetailDTO> getLessonDetail(Long lessonSeq) throws FindException{
    	List<Object[]> Llist = lRepository.getLessonDetail(lessonSeq);
    	List<LessonDTO.selectDetailDTO> dto = new ArrayList<>();
    	TutorDTO tDTO = new TutorDTO();
    	LessonDTO.selectDetailDTO lDTO = new LessonDTO.selectDetailDTO();
    	UsersDTO uDTO = new UsersDTO();
    	
    	lDTO.setLessonName((String)Llist.get(0)[0]);
    	lDTO.setLocation((String)Llist.get(0)[2]);
    	lDTO.setImgPath((String)Llist.get(0)[1]);
    	lDTO.setStartCdate((Date)Llist.get(0)[3]);
    	lDTO.setEndCdate((Date)Llist.get(0)[4]);
    	BigDecimal category = (BigDecimal)Llist.get(0)[5];
    	BigDecimal people = (BigDecimal)Llist.get(0)[6];
    	Integer categoryToInt = category.intValue();
    	Integer peopleToInt = people.intValue();
    	
    	lDTO.setCategory(categoryToInt);
    	lDTO.setPeople(peopleToInt);
    	uDTO.setName((String)Llist.get(0)[7]);
    	tDTO.setUdto(uDTO);
    	lDTO.setTDTO(tDTO);
    	dto.add(lDTO);
    	
    	return dto;
    }
    
    /**
     * 클래스 삭제할 때, 찾아오기 위한 단순 검색용
     * @author Jin
     * @param lessonSeq
     * @return
     * @throws FindException
     */
    public LessonDTO.selectDetailDTO selectLesson(Long lessonSeq) throws FindException{
    	Optional<Lesson> optLesson = lRepository.findById(lessonSeq);
    	if(optLesson.isPresent()) {
    		Lesson lesson = optLesson.get();
    		ModelMapper modelMapper = new ModelMapper();
    		LessonDTO.selectDetailDTO lessonDTO = modelMapper.map(lesson, LessonDTO.selectDetailDTO.class);
    		
    		return lessonDTO;
    	} else {
    		throw new FindException("수업 번호가 없습니다.");
    	}
    }
    
    /**
     * 수업 추가, 수정(지원님과 병합 충돌 예정->지원님껄로)
     * @author Jin
     * @param lDTO
     * @throws FindException
     */
    public void updates(LessonDTO.selectDetailDTO lDTO) throws FindException{
    	Optional<Lesson> l = lRepository.findById(lDTO.getLessonSeq());
    	Lesson lEntity = new Lesson();
    	ModelMapper modelMapper = new ModelMapper();
    	modelMapper.map(lDTO, lEntity);
    	lRepository.save(lEntity);
    }
    
    /** 
     * 수업 삭제(payLesson을 2로 변경)
     * @author Jin
     * @param lessonSeq
     * @throws FindException
     */
	public void deleteLesson(Long lessonSeq) throws FindException{
		LessonDTO.selectDetailDTO lessonDTO = this.selectLesson(lessonSeq);
    	ModelMapper modelMapper = new ModelMapper();
		lessonDTO.setPayLesson(2);
		Lesson lessonEntity = new Lesson();
		
		modelMapper.map(lessonDTO, lessonEntity);
		lRepository.save(lessonEntity);
	}
	
}


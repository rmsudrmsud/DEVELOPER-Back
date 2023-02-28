package com.developer.appliedlesson.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.appliedlesson.repository.AppliedLessonRepository;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.repository.LessonRepository;
import com.developer.users.dto.UsersDTO;

@Service
public class AppliedLessonService {

	@Autowired
	private AppliedLessonRepository appliedLessonRepository;
	
	@Autowired
	private LessonRepository lessonRepository;
	
	
	/**
	 * 클래스 신청내역 정보 불러오기
	 * @author ?
	 * @param applySeq
	 * @return
	 * @throws FindException
	 */
	public AppliedLessonDTO.selectAppliedLessonDTO selectAppliedLesson(Long applySeq) throws FindException{
		Optional<AppliedLesson> optAppliedLesson = appliedLessonRepository.findById(applySeq);
		if(optAppliedLesson.isPresent()) {
			AppliedLesson appliedLesson = optAppliedLesson.get();
			ModelMapper modelMapper = new ModelMapper();
			AppliedLessonDTO.selectAppliedLessonDTO appliedLessonDTO = modelMapper.map(appliedLesson, AppliedLessonDTO.selectAppliedLessonDTO.class);
		
			return appliedLessonDTO;
		} else {
			throw new FindException("수업 신청 내역을 찾을 수 없습니다.");
		}
		
	}
	
	/**
	 * 내 클래스에 신청한 튜티 승인하기
	 * @author Jin
	 * @param applySeq
	 * @throws FindException
	 */
	public void updateApplyLesson(Long applySeq) throws FindException{
		AppliedLessonDTO.selectAppliedLessonDTO appliedLessonDTO = this.selectAppliedLesson(applySeq);
		ModelMapper modelMapper = new ModelMapper();
		appliedLessonDTO.setApplyOk(1);
		AppliedLesson appliedLessonEntity = new AppliedLesson();
		modelMapper.map(appliedLessonDTO, appliedLessonEntity);
		appliedLessonRepository.save(appliedLessonEntity);	
	}
	
	/**
	 * 내 클래스에 신청한 튜티 거절하기 
	 * @author Jin
	 * @param applySeq
	 * @throws FindException
	 */
	public void updateNotApplyLesson(Long applySeq) throws FindException{
		AppliedLessonDTO.selectAppliedLessonDTO appliedLessonDTO = this.selectAppliedLesson(applySeq);
		ModelMapper modelMapper = new ModelMapper();
		appliedLessonDTO.setApplyOk(2);
		AppliedLesson appliedLessonEntity = new AppliedLesson();
		modelMapper.map(appliedLessonDTO, appliedLessonEntity);
		appliedLessonRepository.save(appliedLessonEntity);	
	}
	
	/**
	 * 승인대기중인 튜티리스트 출력
	 * @author Jin
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
	public List<AppliedLessonDTO.UserByAppliedLessonDTO> getLessonNotApplyUser(Long lessonSeq) throws FindException{
		List<Object[]> Alist = appliedLessonRepository.getLessonNotApplyUser(lessonSeq);
		List<AppliedLessonDTO.UserByAppliedLessonDTO> dto = new ArrayList<>();
		for(int i=0; i<Alist.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			AppliedLessonDTO.UserByAppliedLessonDTO aDTO = new AppliedLessonDTO.UserByAppliedLessonDTO();
			LessonDTO.selectDetailDTO lDTO = new LessonDTO.selectDetailDTO();
			uDTO.setName((String)Alist.get(i)[0]);
			aDTO.setApplyOk(0);
			aDTO.setTuteeId((String)uDTO.getUserId());
			lDTO.setLessonSeq(lessonSeq);
			aDTO.setUsersDTO(uDTO);
			aDTO.setLessonDTO(lDTO);
			dto.add(aDTO);
		}
		return dto;
	}
	
	/**
	 * 승인된 튜티리스트 출력
	 * @author Jin
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
	public List<AppliedLessonDTO.UserByAppliedLessonDTO> getLessonApplyUser(Long lessonSeq) throws FindException{
		List<Object[]> Alist = appliedLessonRepository.getLessonApplyUser(lessonSeq);
		List<AppliedLessonDTO.UserByAppliedLessonDTO> dto = new ArrayList<>();
		for(int i=0; i<Alist.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			AppliedLessonDTO.UserByAppliedLessonDTO aDTO = new AppliedLessonDTO.UserByAppliedLessonDTO();
			LessonDTO.selectDetailDTO lDTO = new LessonDTO.selectDetailDTO();
			uDTO.setName((String)Alist.get(i)[0]);
//			aDTO.setApplyOk(1);
			aDTO.setTuteeId((String)uDTO.getUserId());
			lDTO.setLessonSeq(lessonSeq);
			aDTO.setUsersDTO(uDTO);
			aDTO.setLessonDTO(lDTO);
			dto.add(aDTO);
		}
		return dto;
	}
	
	
}

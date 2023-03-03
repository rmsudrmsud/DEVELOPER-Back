package com.developer.appliedlesson.service;

import java.math.BigDecimal;
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
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class AppliedLessonService {

	@Autowired
	private AppliedLessonRepository alRepository;
	
	@Autowired
	private UsersRepository uRepository;
	
	@Autowired
	private LessonRepository lRepository;
	
	
	/**
	 * 클래스 신청내역 정보 불러오기
	 * @author Jin
	 * @param applySeq
	 * @return
	 * @throws FindException
	 */
	public AppliedLessonDTO.selectAppliedLessonDTO selectAppliedLesson(Long applySeq) throws FindException{
		Optional<AppliedLesson> optAppliedLesson = alRepository.findById(applySeq);
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
		alRepository.save(appliedLessonEntity);	
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
		alRepository.save(appliedLessonEntity);	
	} 
	
	/**
	 * 수업 신청
	 * @author moonone
	 * @param dto 신청정보 
	 * @param lessonSeq 신청하는 수업번호
	 * @param logined 사용자아이디
	 */
	public void applyLesson(AppliedLessonDTO.alAddRequestDTO dto, Long lessonSeq, String logined) {
		
		AppliedLesson alEntity = new AppliedLesson();
		alEntity.setApplyOk(dto.getApplyOk());
		alEntity.setApplySeq(dto.getApplySeq());
		alEntity.setCdate(dto.getCdate());
		alEntity.setTuteeId(logined);
		Optional<Users> optU = uRepository.findById(logined);
		alEntity.setUsers(optU.get());
		Optional<Lesson> optL = lRepository.findById(lessonSeq);
		alEntity.setLesson(optL.get());	
		
		alRepository.save(alEntity);
	}
	
	/**
	 * 승인대기중인 튜티리스트 출력
	 * @author Jin
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
	public List<AppliedLessonDTO.NotYetUserByAppliedLessonDTO> getLessonNotApplyUser(Long lessonSeq) throws FindException{
		List<Object[]> Alist = alRepository.getLessonNotApplyUser(lessonSeq);
		List<AppliedLessonDTO.NotYetUserByAppliedLessonDTO> dto = new ArrayList<>();
		for(int i=0; i<Alist.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			AppliedLessonDTO.NotYetUserByAppliedLessonDTO aDTO = new AppliedLessonDTO.NotYetUserByAppliedLessonDTO();
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
	public List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> getLessonApplyUser(Long lessonSeq) throws FindException{
		List<Object[]> Alist = alRepository.getLessonApplyUser(lessonSeq);
		List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> dto = new ArrayList<>();
		for(int i=0; i<Alist.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			AppliedLessonDTO.ApproveUserByAppliedLessonDTO aDTO = new AppliedLessonDTO.ApproveUserByAppliedLessonDTO();
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
	
	
	public List<UsersDTO.getNameDTO> selectClassAndTutee(Long lessonSeq) throws FindException{
		List<Object[]> list = alRepository.selectClassAndTutee(lessonSeq);
		List<UsersDTO.getNameDTO> dtoList = new ArrayList<>();
		for(int i = 0; i<list.size(); i++) {
		UsersDTO.getNameDTO uDTO = new UsersDTO.getNameDTO();
		uDTO.setUsername((String) list.get(i)[1]);
		LessonDTO.getLessonNameDTO lDTO = new LessonDTO.getLessonNameDTO();
		lDTO.setLessonName((String) list.get(i)[0]);
		uDTO.setLessonName(lDTO);
		dtoList.add(uDTO);
		}
		return dtoList;
	}
	
	/**
	 * 진행완료된 클래스 페이지 후기 전체목록 
	 * @author choigeunhyeong
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	public List<UsersDTO.getCompletedClassDTO> selectCompletedClassList(String userId) throws FindException{
		List<Object[]> list = alRepository.selectCompletedClassList(userId);
		List<UsersDTO.getCompletedClassDTO> dtoList = new ArrayList<>();
		for(int i = 0; i<list.size(); i++) {
			UsersDTO.getCompletedClassDTO gDTO = new UsersDTO.getCompletedClassDTO();
			gDTO.setUsername((String) list.get(i)[0]);
			LessonReviewDTO.getReviewList lDTO = new LessonReviewDTO.getReviewList();
			lDTO.setReview((String) list.get(i)[1]);
			
			BigDecimal star = (BigDecimal)list.get(i)[2];
			int resultstar = star.intValue();
			lDTO.setStar(resultstar);
			gDTO.setReview(lDTO);
			dtoList.add(gDTO);
		}
		return dtoList;
	}
}

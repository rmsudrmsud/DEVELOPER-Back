package com.developer.appliedlesson.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppliedLessonService {
	private final AppliedLessonRepository alRepository;
	private final UsersRepository uRepository;
	private final LessonRepository lRepository;
 
	
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
	 * 진행완료된 클래스 페이지 클래스명, 수강했던 튜티목록
	 * @author choigeunhyeong
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
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

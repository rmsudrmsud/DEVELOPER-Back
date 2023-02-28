package com.developer.appliedlesson.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.appliedlesson.repository.AppliedLessonRepository;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.users.dto.UsersDTO;

@Service
public class AppliedLessonService {
	@Autowired
	AppliedLessonRepository apliedLessonRepository;
	
	/**
	 * 진행완료된 클래스 페이지 클래스명, 수강했던 튜티목록
	 * @author choigeunhyeong
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
	public List<UsersDTO.getNameDTO> selectClassAndTutee(Long lessonSeq) throws FindException{
		List<Object[]> list = apliedLessonRepository.selectClassAndTutee(lessonSeq);
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
		List<Object[]> list = apliedLessonRepository.selectCompletedClassList(userId);
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

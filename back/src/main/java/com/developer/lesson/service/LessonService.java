package com.developer.lesson.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonRequestDTO;
import com.developer.lesson.dto.LessonRequestDTO.selectAllBydateLessonDTO;
import com.developer.lesson.repository.LessonRepository;
import com.developer.tutor.repository.TutorRepository;

@Service
public class LessonService {
	@Autowired
	LessonRepository lRepository;
	@Autowired
	private TutorRepository tRepository;


	/**
	 * [메인페이지] 신청종료날짜 임박순으로 list를 출력한다.
	 * @author SR
	 * @return List<Object[]>
	 * @throws FindException
	 */
	public List<selectAllBydateLessonDTO> selectAllByDateLesson() throws FindException {
		List<Object[]> lList = lRepository.selectAllBydateLesson();

		List<LessonRequestDTO.selectAllBydateLessonDTO> lListDto = new ArrayList<>();
		for (int i = 0; i < lList.size(); i++) {
			LessonRequestDTO.selectAllBydateLessonDTO lDto = new LessonRequestDTO.selectAllBydateLessonDTO();
			BigDecimal lessonSeq = (BigDecimal)lList.get(i)[0];
			long convertSeq = lessonSeq.longValue();
			lDto.setLessonSeq(convertSeq);
			lDto.setLessonName((String)lList.get(i)[1]);
			lDto.setImgPath((String)lList.get(i)[2]);
			BigDecimal price = (BigDecimal)lList.get(i)[3];
			int convertPrice = price.intValue();
			lDto.setPrice(convertPrice);
			
			lListDto.add(lDto);
		}
		return lListDto;
	}

}

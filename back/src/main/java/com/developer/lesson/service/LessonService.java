package com.developer.lesson.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonRequestDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;

@Service
public class LessonService {
	@Autowired
	LessonRepository lRepository;
	
	// [JW] 선택한 클래스에 대한 상세 정보
	public Lesson selectDetail(Long lessonSeq) throws FindException {
		Optional<Lesson> optL = lRepository.findById(lessonSeq);
		return optL.get();
	};
	
	// [JW] 수업 등록
	public void addLesson(LessonRequestDTO dto) throws FindException {
		
	}
	
	// [JW] 클래스를 개설한 튜터의 후기 목록
	// [JW] 튜터가 생성한 클래스 목록 + 튜터 정보
	// [JW] 현재 진행 중인 수업 목록 > 카테고리 검색까지는 가능, 필터는 아직
	// [JW] 수업 이름, 카테고리명, 강사명 검색 > 제목 검색은 가능
}

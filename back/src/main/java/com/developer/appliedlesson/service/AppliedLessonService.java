package com.developer.appliedlesson.service;

import org.springframework.stereotype.Service;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.repository.AppliedLessonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppliedLessonService {
	
	private final AppliedLessonRepository alRepository;
	
	//[JW] 수업 신청
	public void addLesson(AppliedLessonDTO.alAddRequestDTO dto) {
		
	}
	
}

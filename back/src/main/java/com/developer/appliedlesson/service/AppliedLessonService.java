package com.developer.appliedlesson.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.appliedlesson.repository.AppliedLessonRepository;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;
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
	
}

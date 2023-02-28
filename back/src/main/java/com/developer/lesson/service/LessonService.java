package com.developer.lesson.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;
import com.developer.tutor.dto.TutorDTO;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;
import com.developer.users.dto.UsersDTO;

@Service
public class LessonService {
	@Autowired
	LessonRepository lRepository;
	@Autowired
	private TutorRepository tRepository;
	
	// [JW] 선택한 클래스에 대한 상세 정보
	public Lesson selectDetail(Long lessonSeq) throws FindException {
		Optional<Lesson> optL = lRepository.findById(lessonSeq);
		return optL.get();
	};
	
	// [JW] 수업 등록
	public void addLesson(Lesson lesson, String userId) throws FindException {
		Optional<Tutor> t = tRepository.findById(userId);
		Tutor tutor = t.get();
		lesson.setTutor(tutor);
		lRepository.save(lesson);
	}
	
    public List<LessonDTO> getLessonByUser3(String tutorId) throws FindException{
        List<Object[]> Llist = lRepository.getLessonByUser3(tutorId);
        List<LessonDTO> dto = new ArrayList<>();
        for(int i=0; i<Llist.size(); i++) {
           TutorDTO tDTO = new TutorDTO();
           LessonDTO lDTO = new LessonDTO();
           UsersDTO uDTO = new UsersDTO();
           lDTO.setLessonName((String)Llist.get(i)[0]);
           tDTO.setTutorId((String)tutorId);
         //  uDTO.setUserId((String)tutorId);
           tDTO.setUdto(uDTO);
           lDTO.setTDTO(tDTO);
           dto.add(lDTO);
           
        }
        return dto;
     }
}

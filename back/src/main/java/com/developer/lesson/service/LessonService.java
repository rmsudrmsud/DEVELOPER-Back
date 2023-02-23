package com.developer.lesson.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;
import com.developer.tutor.dto.TutorDTO;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;

@Service
public class LessonService {
	private final LessonRepository lRepository;
    private final ModelMapper modelMapper;

	@Autowired
	public LessonService(LessonRepository lRepository, ModelMapper modelMapper) {
		this.lRepository = lRepository;
		this.modelMapper = modelMapper;
	}
	@Autowired
	public TutorRepository tRepository;
	
	// [JW] 선택한 클래스에 대한 상세 정보
	public LessonDTO selectDetail(Long lessonSeq) throws FindException {
		Optional<Lesson> optL = lRepository.findById(lessonSeq); //JPA메서드로 원하는 결과값 꺼내
		Lesson lEntity = optL.get(); //결과값 Lesson Entity로 담아주기 
		
		//Entity를 DTO로 바꿔서 전달해줘야하니까 modelMapper 사용하기.
		LessonDTO lDTO = modelMapper.map(lEntity, LessonDTO.class); 
		
		Tutor tEntity = lEntity.getTutor(); //has-a 관계인 Tutor값을 결과값(lEntity)꺼내서  
		TutorDTO TDTO = modelMapper.map(tEntity, TutorDTO.class); //반환할 DTO(LessonDTO)에 넣어주기
		lDTO.setTDTO(TDTO);
		
		return lDTO;
	};
	
	// [JW] 수업 등록
	public void addLesson(LessonDTO dto, String userId) throws FindException {
		Optional<Tutor> t = tRepository.findById(userId);
		Tutor tutor = t.get();
		Lesson lesson = modelMapper.map(dto, Lesson.class);
		System.out.println(lesson);
		lRepository.save(lesson);
	}
	
	// [JW] 클래스를 개설한 튜터의 후기 목록
	public List<Object[]> selectAllReview(Long lessonSeq) throws FindException{
		List<Object[]> list = lRepository.selectAllReview(lessonSeq);
		return list;
	}
	
	// [JW] 튜터가 생성한 클래스 목록 + 튜터 정보
	public  List<Object[]> selectTutorDetail(String userId) throws FindException{
		List<Object[]> list = lRepository.selectTutorDetail(userId);
		return list;
	}
	
	
	// [JW] 현재 진행 중인 수업 목록 > 카테고리 검색까지는 가능, 필터는 아직
	// [JW] 수업 이름, 카테고리명, 강사명 검색 > 제목 검색은 가능
}

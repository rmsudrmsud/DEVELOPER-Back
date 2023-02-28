package com.developer.lesson.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.dto.LessonDTO.selectAllBydateLessonDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.tutor.dto.TutorDTO;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;
import com.developer.users.dto.UsersDTO;

@Service
public class LessonService {
	
	ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private LessonRepository lRepository;
	@Autowired
	private TutorRepository tRepository;

    
	/**
	 * 선택한 수업에 대한 상세 정보
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 수업 상세정보 + 튜터 정
	 * @throws FindException
	 */
	public LessonDTO.selectDetailDTO selectDetail(Long lessonSeq) throws FindException {
		Optional<Lesson> optL = lRepository.findById(lessonSeq); //JPA메서드로 원하는 결과값 꺼내
		Lesson lessonEntity = optL.get(); //결과값 Lesson Entity로 담아주기 
		//Entity를 DTO로 바꿔서 전달해줘야하니까 modelMapper 사용하기.
		LessonDTO.selectDetailDTO lessonDto = modelMapper.map(lessonEntity, LessonDTO.selectDetailDTO.class); 
		Tutor tutorEntity = lessonEntity.getTutor(); //has-a 관계인 Tutor값을 결과값(lEntity)꺼내서  
		TutorDTO tutorDto = modelMapper.map(tutorEntity, TutorDTO.class); //반환할 DTO(LessonDTO)에 넣어주기
		lessonDto.setTDTO(tutorDto);
		return lessonDto;
	};
	
	/**
	 * 수업 등록 및 수정
	 * @author moonone
	 * @param dto 사용자가 입력한 등록 및 수정 정보값 
	 * @param userId 사용자 아이디
	 * @throws FindException
	 */
	public void addLessonDTO(LessonDTO dto, String userId) throws FindException {		
		Optional<Lesson> optl = lRepository.findById(dto.getLessonSeq());
		Optional<Tutor> optT = tRepository.findById(userId);
		Tutor tutor = optT.get();
		Lesson lesson = new Lesson();
		lesson.setTutor(tutor);
		modelMapper.map(dto, lesson);
		lesson = lRepository.save(lesson);
	}
	
	/**
	 * 튜터가 받은 후기 목록
	 * @author moonone
	 * @param lessonSeq 튜터의 수업번호
	 * @return 후기 목록
	 * @throws FindException
	 */
	public List<LessonDTO.selectAllReviewDTO> selectAllReview(Long lessonSeq) throws FindException{
		
		List<Object[]> lList = lRepository.selectAllReview(lessonSeq); 
		List<LessonDTO.selectAllReviewDTO> dto = new ArrayList<>() ;
		
		for(int i=0; i<lList.size(); i++) { 

			LessonDTO.selectAllReviewDTO lDto = new LessonDTO.selectAllReviewDTO();
			lDto.setLessonName((String)lList.get(i)[4]);  

			TutorDTO.tDTO tDto = new TutorDTO.tDTO(); 	
			UsersDTO.uNameDTO uDto = new UsersDTO.uNameDTO();
			uDto.setName((String)lList.get(i)[5]);  
			tDto.setUDTO(uDto); 
			 
			List<AppliedLessonDTO.alLessonDTO> alList = new ArrayList<>();  
			AppliedLessonDTO.alLessonDTO alDto = new AppliedLessonDTO.alLessonDTO(); 
			alDto.setTuteeId((String)lList.get(i)[3]); 
			
			LessonReviewDTO.lrALDTO rDto = new LessonReviewDTO.lrALDTO();
			BigDecimal star = (BigDecimal) lList.get(i)[2];
			Integer result = star.intValue();
			rDto.setStar(result);
			rDto.setReview((String)lList.get(i)[1]);
			rDto.setCDate((Date)lList.get(i)[0]);
			
			alDto.setLrdto(rDto); 
			alList.add(alDto);

			lDto.setName(tDto.getUDTO().getName());
			lDto.setAlDTO(alList);
			
			dto.add(lDto);
			}
		return dto;
	}
	
	/**
	 * 모든 수업 목록
	 * @author moonone
	 * @return 수업 목록
	 */
	public List<LessonDTO.allLessonListDTO> allLessonList(){
		List<Object[]> list = lRepository.selectAllLesson();
		List<LessonDTO.allLessonListDTO> lesson = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			LessonDTO.allLessonListDTO dto = new LessonDTO.allLessonListDTO();
			dto.setLessonName((String)list.get(i)[6]);
			dto.setCategory(((BigDecimal)list.get(i)[1]).intValue());
			dto.setPayLesson(((BigDecimal)list.get(i)[8]).intValue());
			dto.setPrice(((BigDecimal)list.get(i)[10]).intValue());
			dto.setTutorId((String)list.get(i)[13]);
			lesson.add(dto);
			}
		return lesson;
	}

	/**
	 * 수업 이름 검색
	 * @author moonone
	 * @param searchKeyword 검색할 단어
	 * @return 해당하는 값 
	 */
	public List<LessonDTO.searchLessonDTO> findByLessonNameContaining(String searchKeyword){
		List<Object[]> list = new ArrayList<>();
		if(searchKeyword == null) { //전체검색
			list = lRepository.selectAllLesson();
		} else { //제목검색 
			list = lRepository.findByLessonNameContaining(searchKeyword);
		}
		List<LessonDTO.searchLessonDTO> lesson = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			LessonDTO.searchLessonDTO dto = new LessonDTO.searchLessonDTO();
			dto.setLessonName((String)list.get(i)[0]);
			dto.setCategory(((BigDecimal)list.get(i)[1]).intValue());
			dto.setImgPath((String)list.get(i)[2]);
			dto.setStartCdate((LocalDate)list.get(i)[3]);
			dto.setEndCdate((LocalDate)list.get(i)[4]);
			dto.setCategory(((BigDecimal)list.get(i)[5]).intValue());
			lesson.add(dto);
		}
		return lesson;
	}

	/**
	 * [메인페이지] 신청종료날짜 임박순으로 list를 출력한다.
	 * @author SR
	 * @return List<Object[]>
	 * @throws FindException
	 */
	public List<selectAllBydateLessonDTO> selectAllByDateLesson() throws FindException {
		List<Object[]> lList = lRepository.selectAllBydateLesson();

		List<LessonDTO.selectAllBydateLessonDTO> lListDto = new ArrayList<>();
		for (int i = 0; i < lList.size(); i++) {
			LessonDTO.selectAllBydateLessonDTO lDto = new LessonDTO.selectAllBydateLessonDTO();
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
	/**
	 * [DS] 관리자 대시보드 최신 생성된 클래스 TOP5
	 * @author ds
	 * @return List<LessonDTO.LessonList5DTO>
	 * @throws FindException
	 */
	public List<LessonDTO.LessonList5DTO> selectList5()throws FindException{
		List<Object[]> list = lRepository.selectClassList5();
		List<LessonDTO.LessonList5DTO> dto = new ArrayList<>();
		for(int i=0; i<list.size();i++) {
			LessonDTO.LessonList5DTO llDTO = new LessonDTO.LessonList5DTO();
			llDTO.setLessonName((String)list.get(i)[0]);
			llDTO.setCategory(Integer.parseInt(String.valueOf(list.get(i)[1])));
			llDTO.setPeople(Integer.parseInt(String.valueOf(list.get(i)[2])));
			dto.add(llDTO);
			
		}
		return dto;
	}

}

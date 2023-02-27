package com.developer.lesson.service;

import java.math.BigDecimal;
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
import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.tutor.dto.TutorDTO;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;
import com.developer.users.dto.UsersDTO;
import com.developer.users.repository.UsersRepository;

@Service
public class LessonService {	
	
	@Autowired
	private LessonRepository lRepository;
	@Autowired
	public TutorRepository tRepository;
	@Autowired
	public UsersRepository uRepository;
    @Autowired
	private ModelMapper modelMapper;
	
	// [JW] 선택한 클래스에 대한 상세 정보
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
	
	// [JW] 수업 등록
	public void addLessonDTO(LessonDTO dto, String userId) throws FindException {
		Optional<Tutor> optT = tRepository.findById(userId);
		Tutor tutor = optT.get();
		Lesson lesson = new Lesson();
		lesson.setTutor(tutor);
		modelMapper.map(dto, lesson);
		lesson = lRepository.save(lesson);
	}
	
	// [JW] 튜터가 받은 후기 목록
	public List<LessonDTO.selectAllReviewDTO> selectAllReview(Long lessonSeq) throws FindException{
		
		//내가 꺼내고자하는 결과값 = nativeQuery 
		//이건 Object[] 입니다. 그래서 이걸 내가 만들어낸 DTO로 바꿔서 반환해야하는 거예요.
		List<Object[]> lList = lRepository.selectAllReview(lessonSeq); 
		
		//프론트로 보내 값을 담을 빈 배열 생성. 
		//타입은 해당 메서드의 반환타입과 동일합니다. 
		List<LessonDTO.selectAllReviewDTO> dto = new ArrayList<>() ;
		
		for(int i=0; i<lList.size(); i++) { //List이기 때문에 for문 안에서 진행되어야 합니다. 
			
			/*
				본인이 만든 DTO에 맞도록 값을 매핑해서 넣어주는 것이 포인트 ***
				그래서 원하는 값을 매핑할 수 있는 DTO를 생성하고, 거기에 lRepository에서 꺼내온 값을 넣어주는 거예요. 
				
				제가 만든 selectAllReviewDTO에는
				
						private String lessonName;
						private List<AppliedLessonDTO> alDTO;
						private TutorDTO tDTO;
						
				다음과 같은 멤버변수가 있어요. 
				그래서 이제 얘네에 해당하는 DTO를 생성하고, 꺼내온 값을 연결시켜줄겁니다. 
			 */
			
			//반환해줄 DTO
			LessonDTO.selectAllReviewDTO lDto = new LessonDTO.selectAllReviewDTO();
			lDto.setLessonName((String)lList.get(i)[4]); //이거는 바로 DTO에 넣어줄 수 있으니까 바로 set합니다. 
		
			//user의 이름을 매핑하기 위한 과정 
			TutorDTO.tDTO tDto = new TutorDTO.tDTO(); 		//TutorDTO 생성 
			UsersDTO uDto = new UsersDTO();	//UsersDTO 생성
			uDto.setName((String)lList.get(i)[5]);  //Object 배열의 5번째 방에 있는 name값을 set 
			tDto.setUDTO(uDto); // TutorDTO안에 UsersDTO가 있으니까 set으로 넣어주기
			
			//TuteeId를 매핑하기 위한 과정 
			List<AppliedLessonDTO.alLessonDTO> alList = new ArrayList<>(); 	//List<AppliedLessonDTO> 생성 
			AppliedLessonDTO.alLessonDTO alDto = new AppliedLessonDTO.alLessonDTO();  	//list 안의 AppliedLessonDTO에 접근
			alDto.setTuteeId((String)lList.get(i)[3]); //아이디값 넣어주기
			
			//나머지 값들을 매핑하기 위한 과정
			LessonReviewDTO.lrALDTO rDto = new LessonReviewDTO.lrALDTO();	//LessonReviewDTO 생성
			//Object[]에서 꺼내줘서 Integer로 바로 반환이 안되더라고요. 
			//BigDecimal로 반환하고 다시 Integer로 형변환 합니다. 
			BigDecimal star = (BigDecimal) lList.get(i)[2];
			Integer result = star.intValue();
			rDto.setStar(result);
			rDto.setReview((String)lList.get(i)[1]);
			rDto.setCDate((Date)lList.get(i)[0]);
			
			alDto.setLrdto(rDto); //값 다 넣었으니까 AppliedLessonDTO에 넣어주기. 
			
			//AppliedLessonDTO에 LessonReviewDTO이 들어있으니까 모든게 끝나고 add해줍니다. 
			alList.add(alDto);
			
			//---------------------------------------------------------
			//값을 다 얻었으면 결과적으로 우리가 반환하고 싶었던 DTO에 set 해줍니다. 
			lDto.setName(tDto.getUDTO().getName());
			lDto.setAlDTO(alList);
			
			//그리고 최종 반환타입이 List니까, 추가적으로 한 번 더 add합니다. 
			dto.add(lDto);
			}
		
			//왜 이런 짓을 해야하느냐 ?  has-a관계가 너무 ... 얽혀있기 때문.
		
		return dto;
	}
	
	// [JW] 튜터가 생성한 클래스 목록 + 튜터 정보
	public  List<Object[]> selectTutorDetail(String userId) throws FindException{
		List<Object[]> list = lRepository.selectTutorDetail(userId);
		return list;
	}
	
	// [JW] 현재 진행 중인 수업 목록 > 카테고리 검색까지는 가능, 필터는 아직
	// [JW] 수업 이름, 카테고리명, 강사명 검색 > 제목 검색은 가능
}

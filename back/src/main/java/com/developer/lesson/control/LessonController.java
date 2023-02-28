package com.developer.lesson.control;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.dto.LessonDTO.selectDetailDTO;
import com.developer.lesson.service.LessonService;

@RestController
@RequestMapping("lesson/*")
public class LessonController {
	@Autowired
	private LessonService lservice;
	
	//[JH]
	@GetMapping(value = "getlesson1/{tutorId}" )
	public ResponseEntity<?> getLessonByUser1(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser> list = lservice.getLessonByUser1(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	//[JH]
	@GetMapping(value = "getlesson2/{tutorId}" )
	public ResponseEntity<?> getLessonByUser2(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser> list = lservice.getLessonByUser2(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	//[JH]
	@GetMapping(value = "getlesson3/{tutorId}" )
	public ResponseEntity<?> getLessonByUser3(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser> list = lservice.getLessonByUser3(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	//[JH]
	@GetMapping(value = "getapply/{userId}")
	public ResponseEntity<?> getApplyLesson(@PathVariable String userId) throws FindException{
		List<LessonDTO.applyLessonBytutee> list = lservice.getApplyLesson(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//[JH]
	@GetMapping(value = "upcoming/{userId}")
	public ResponseEntity<?> upComingLesson(@PathVariable String userId) throws FindException{
		List<LessonDTO.applyLessonBytutee> list = lservice.upComingLesson(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//[JH]
	@GetMapping(value = "ongoing/{userId}")
	public ResponseEntity<?> onGoinLesson(@PathVariable String userId) throws FindException{
		List<LessonDTO.applyLessonBytutee> list = lservice.onGoingLesson(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	//[JH]
	@GetMapping(value = "lessondetail/{lessonSeq}")
	public ResponseEntity<?> getLessonDetail(@PathVariable Long lessonSeq) throws FindException{
		List<LessonDTO.selectLessonDTO> list = lservice.getLessonDetail(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//[JH]
	@GetMapping(value = "selectlesson/{lessonSeq}")
	public ResponseEntity<?> selectLesson(@PathVariable Long lessonSeq) throws FindException{
		if(lessonSeq == null) {
			return new ResponseEntity<>("수업이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			LessonDTO.selectLessonDTO lessonDTO = lservice.selectLesson(lessonSeq);
			return new ResponseEntity<>(lessonDTO, HttpStatus.OK);
		}
	}
	
	//[JH]
	@PatchMapping(value = "update/{lessonSeq}")
	public ResponseEntity<?> updateLesson(@PathVariable Long lessonSeq,@RequestBody LessonDTO.selectLessonDTO lDTO, HttpSession session) throws FindException {
		lservice.updates(lDTO);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	//[JH]
	@PatchMapping(value = "delete/{lessonSeq}")
	public ResponseEntity<?> deleteLesson(@PathVariable Long lessonSeq, HttpSession session) throws FindException{
		if(lessonSeq == null) {
			return new ResponseEntity<>("수업 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			lservice.deleteLesson(lessonSeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}


	/**
	 * 선택한 클래스에 대한 상세 정보
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 상세정보 
	 * @throws FindException
	 */
	@GetMapping (value = {"detail/{lessonSeq}"}, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<LessonDTO.selectDetailDTO> detail(@PathVariable Long lessonSeq) throws FindException{
		selectDetailDTO lessonDto = lservice.selectDetail(lessonSeq);
		return new ResponseEntity<LessonDTO.selectDetailDTO>(lessonDto, HttpStatus.OK);
	}
	
	/**
	 * 수업 등록 및 수정
	 * @author moonone
	 * @param dto 사용자가 입력한 등록 및 수정 정보값 
	 * @param userId 사용자 아이디
	 * @throws FindException
	 */
	@PostMapping(value = {"{userId}"}, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<LessonDTO> add(@RequestBody LessonDTO.selectDetailDTO dto, @PathVariable String userId) throws AddException, FindException{
		lservice.addLessonDTO(dto, userId);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	/**
	 * 튜터가 받은 후기 목록
	 * @author moonone
	 * @param lessonSeq 튜터의 수업번호
	 * @return 후기 목록
	 * @throws FindException
	 */
	@GetMapping(value = {"{lessonSeq}"})
	public ResponseEntity<?> review(@PathVariable Long lessonSeq) throws FindException{
		List<LessonDTO.selectAllReviewDTO> list = lservice.selectAllReview(lessonSeq);
		return new ResponseEntity<>(list ,HttpStatus.OK);
	}
	
	/**
	 * 모든 수업 목록
	 * @author moonone
	 * @return 수업 목록
	 */
	@GetMapping(value="lessonlist")
	public ResponseEntity<?> allLessonList() throws FindException{
		List<LessonDTO.allLessonListDTO> list = lservice.allLessonList();
		return new ResponseEntity<>(list ,HttpStatus.OK);
	}
	
	/**
	 * 수업 이름 검색
	 * @author moonone
	 * @param searchKeyword 검색할 단어
	 * @return 해당하는 값 
	 */
	@GetMapping
	public ResponseEntity<?> searchLesson(@RequestParam String searchWord) throws FindException{
		List<LessonDTO.searchLessonDTO> list = lservice.findByLessonNameContaining(searchWord);
		return new ResponseEntity<>(list ,HttpStatus.OK);
	}

	/**
	 * [메인페이지] 신청종료날짜 임박순으로 list를 출력한다.
	 * @author SR
	 * @return 신청종료남짜 임박순으로 정렬한 list
	 * @throws FindException
	 */
	@GetMapping(value ="listbydate", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<?> ListLessonByDateController() throws FindException{
		List<LessonDTO.selectAllBydateLessonDTO> list = lservice.selectAllByDateLesson();
		if(list.isEmpty()) {
			return new ResponseEntity<>("신청진행중인 수업이 없습니다.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	/**
	 * [관리자] 관리자 대시보드 최신 생성된 수업 5개 리스트
	 * @author ds
	 * @return 최신 생성된 수업 5개 리스트
	 * @throws FindException
	 */
	@GetMapping(value="list5", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<?> getList5()throws FindException{
		List<LessonDTO.LessonList5DTO> list= lservice.selectList5();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}

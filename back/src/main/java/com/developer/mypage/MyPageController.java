package com.developer.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.service.AppliedLessonService;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.service.LessonService;
import com.developer.userreview.dto.UserReviewDTO;
import com.developer.userreview.service.UserReviewService;
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("mypage/*")
@RequiredArgsConstructor
public class MyPageController {

	private final AppliedLessonService alService;
	private final LessonService lService;
	private final UserReviewService urService;
	private final UsersService uService;
	
	//[JH] 튜터메인
	@GetMapping(value = "tutor/{tutorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorMain(@PathVariable String tutorId) throws FindException{
		MyPageDTO.TutorMainDTO dto = new MyPageDTO.TutorMainDTO();
		
		List<LessonDTO.GetLessonByUser1> lList = lService.getLessonByUser1(tutorId);
		List<LessonDTO.GetLessonByUser2> lList2 = lService.getLessonByUser2(tutorId);
		List<LessonDTO.GetLessonByUser3> lList3 = lService.getLessonByUser3(tutorId);
		dto.setList(lList);
		dto.setList2(lList2);
		dto.setList3(lList3);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	//[JH] 튜터 진행예정수업 상세페이지
	@GetMapping(value = "tutor/upcoming/detail/{lessonSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorUpcoming(@PathVariable Long lessonSeq) throws FindException{
		MyPageDTO.TutorUpcomingDTO dto = new MyPageDTO.TutorUpcomingDTO();
		
		List<AppliedLessonDTO.NotYetUserByAppliedLessonDTO> notYetList = alService.getLessonNotApplyUser(lessonSeq);
		List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> approveList = alService.getLessonApplyUser(lessonSeq);
		List<LessonDTO.selectLessonDTO> lList = lService.getLessonDetail(lessonSeq);
		dto.setNotYetList(notYetList);
		dto.setApproveList(approveList);
		dto.setLList(lList);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	//[JH] 튜터 진행중인수업 상세페이지
	@GetMapping(value = "tutor/ongoing/detail/{lessonSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorOngoing(@PathVariable Long lessonSeq) throws FindException{
		MyPageDTO.TutorOngoingDTO dto = new MyPageDTO.TutorOngoingDTO();
		
		List<LessonDTO.selectLessonDTO> lList = lService.getLessonDetail(lessonSeq);
		List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> approveList = alService.getLessonApplyUser(lessonSeq);
		dto.setAlList(approveList);
		dto.setLList(lList);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	//[JH] 튜티 진행 예정 수업 목록(승인, 미승인)
	@GetMapping(value = "tutee/upcoming/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tuteeUpcoming(@PathVariable String userId) throws FindException{
		MyPageDTO.TuteeUpcomingDTO dto = new MyPageDTO.TuteeUpcomingDTO();
		
		List<LessonDTO.applyLessonBytutee> applyList = lService.upComingLesson(userId);
		List<LessonDTO.notYetLessonBytutee> notYetList = lService.getApplyLesson(userId);
		dto.setApplyList(applyList);
		dto.setNotYetlist(notYetList);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	//OK
	//[JH] 내 클래스에 신청한 튜티 승인
	@PatchMapping(value = "tutor/upcoming/detail/apply/{applySeq}")
	public ResponseEntity<?> updateApplyLesson(@PathVariable Long applySeq, HttpSession session) throws FindException {
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청 내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			alService.updateApplyLesson(applySeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}	
	}
	//OK
	//[JH] 내 클래스에 신청한 튜티 거절
	@PatchMapping(value = {"tutor/upcoming/detail/notapply/{applySeq}"})
	public ResponseEntity<?> updateNotApplyLesson(@PathVariable Long applySeq, HttpSession session) throws FindException {
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청 내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			alService.updateNotApplyLesson(applySeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	//[JH] 튜터의 진행예정 수업 리스트 출력(개별페이지에서 필요)
	@GetMapping(value = "tutor/upcoming/{tutorId}" )
	public ResponseEntity<?> getLessonByUser1(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser1> list = lService.getLessonByUser1(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	//[JH] 진행중인 튜터의 수업 리스트 출력(개별페이지에서 필요)
	@GetMapping(value = "tutor/ongoing/{tutorId}" )
	public ResponseEntity<?> getLessonByUser2(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser2> list = lService.getLessonByUser2(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	//[GH] 진행완료된 수업 리스트 출력(개별페이지에서 필요)
	@GetMapping(value = "tutor/completed/{tutorId}" )
	public ResponseEntity<?> getLessonByUser3(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser3> list = lService.getLessonByUser3(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	//[JH] 수업 수정하기
	@PatchMapping(value = "tutor/upcoming/detail/update/{lessonSeq}")
	public ResponseEntity<?> updateLesson(@PathVariable Long lessonSeq,@RequestBody LessonDTO.selectLessonDTO lDTO, HttpSession session) throws FindException {
		lService.updates(lDTO);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	//[JH] 수업 삭제하기
	@PatchMapping(value = "tutor/upcoming/detail/delete/{lessonSeq}")
	public ResponseEntity<?> deleteLesson(@PathVariable Long lessonSeq, HttpSession session) throws FindException{
		if(lessonSeq == null) {
			return new ResponseEntity<>("수업 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			lService.deleteLesson(lessonSeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	//[JH] 튜티가 수강중인 수업
	@GetMapping(value = "tutee/ongoing/{userId}")
	public ResponseEntity<?> onGoinLesson(@PathVariable String userId) throws FindException{
		List<LessonDTO.applyLessonBytutee> list = lService.onGoingLesson(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//[JH] 수업에 참여할 튜티의 이전 수업 후기 목록
	@GetMapping(value = "tutor/upcoming/detail/tuteereview/{userId}")
	public ResponseEntity<?> getTuteeReview(@PathVariable String userId) throws FindException{
		List<UserReviewDTO.getTuteeReview> list = urService.getTuteeReview(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	//[JH] 회원 상세정보
	@GetMapping(value = "{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId) throws FindException{
		if(userId == null) {
			return new ResponseEntity<>("존재하지 않는 회원입니다.",HttpStatus.BAD_REQUEST);
		} else {
			UsersDTO.UsersDetailDTO usersDTO = uService.getUser(userId);
			return new ResponseEntity<>(usersDTO, HttpStatus.OK);
		}
	}
	
	//[JH] 회원 수정하기
	@PatchMapping(value = "update/{userId}")
	public ResponseEntity<?> updateLesson(@PathVariable String userId, @RequestBody UsersDTO uDTO) throws FindException, AddException{
		uService.addUsers(uDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//[JH] 회원 탈퇴하기
	@PatchMapping(value = "delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId, HttpSession session) throws FindException{
		if(userId == null) {
			return new ResponseEntity<>("수업 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			uService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}	
	
	
}

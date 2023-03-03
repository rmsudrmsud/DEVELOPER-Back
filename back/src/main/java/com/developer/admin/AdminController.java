package com.developer.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.hostuser.service.HostUserService;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.service.LessonService;
import com.developer.roominfo.service.RoomInfoService;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.service.StudyroomService;
import com.developer.tutor.service.TutorService;
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("admin/*")
@RequiredArgsConstructor
public class AdminController {
	private final LessonService lservice;
	private final UsersService uService;
	private final TutorService tservice;
	private final HostUserService hService;
	private final RoomInfoService riService;
	private final StudyroomService sService;

	   /**
	    * [관리자 대쉬보드] 스터디카페 최신 순 5, 수업 5개 리스트
	    * @author ds
	    * @return 스터디카페 최신 순 5, 수업 5개 리스트
	    * @throws FindException
	    */
	   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
	   public ResponseEntity<?> getStudyroomList5()throws FindException{
	      AdminDTO.getList5DTO dto = new AdminDTO.getList5DTO();
	      
	      List<StudyroomDTO.StudyroomList5DTO> list1= sService.selectList5();
	      List<LessonDTO.LessonList5DTO> list2= lservice.selectList5();
	      dto.setStudyroomList5DTO(list1);
	      dto.setLessonList5DTO(list2);
	      return new ResponseEntity<>(dto,HttpStatus.OK);
	      
	      
	   }
	   
	   /**
		 * 관리자페이지에서 회원리스트 전체출력
		 * @author DS
		 * @return 회원리스트
		 * @throws FindException
		 */
		@GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> getAllUsers()throws FindException{
		 List<UsersDTO> list	= uService.getALLUsers();
		 return new ResponseEntity<>(list, HttpStatus.OK);
		}
		
		/**관리자페이지 회원리스트에서 검색하기
		 * @author DS
		 * @param userId
		 * @return 검색된 회원정보
		 * @throws FindException
		 */
		@GetMapping(value = "users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> getuserById(@PathVariable String userId)throws FindException{
		 List<UsersDTO> list	= uService.getUserById(userId);
		 return new ResponseEntity<>(list, HttpStatus.OK);
		}
		
		/**관리자페이지 아이디 1개의 상세정보 출력
		 * @author DS
		 * @param userId
		 * @return 검색된 회원정보
		 * @throws FindException
		 */
		@GetMapping(value="users/detail/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> getuserDetail(@PathVariable String userId)throws FindException{
			 UsersDTO dto	= uService.selectUserDetail(userId);
			 return new ResponseEntity<>(dto, HttpStatus.OK);
			}
			
	   
	
	/**
	 * [관리자] 모든 수업 목록
	 * @author moonone
	 * @return 수업 목록
	 */
	@GetMapping(value = "lesson", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> allLessonList(HttpSession session) throws FindException {
		int role = (int) session.getAttribute("loginedRole");
		if(role == 9) {
			List<LessonDTO.allLessonListDTO> list = lservice.allLessonList();
			return new ResponseEntity<>(list, HttpStatus.OK);			
		} else {
			return new ResponseEntity<>("관리자로 로그인하시오", HttpStatus.BAD_REQUEST);						
		}
	}

	/**
	 * [관리자] 수업을 예약한 튜티 목록
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	@GetMapping(value = "lesson/detail/{lessonSeq}")
	public ResponseEntity<?> applyTuteeList(@PathVariable Long lessonSeq) {
		List<UsersDTO.uNameDTO> list = uService.applyTuteeList(lessonSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * [관리자] 수업을 예약한 튜티 삭제
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 * @throws RemoveException 
	 */
	@DeleteMapping(value = "lesson/detail/{lessonSeq}/{tuteeId}")
	public ResponseEntity<?> deleteTutee(@PathVariable Long lessonSeq, @PathVariable String tuteeId) throws RemoveException {
		uService.deleteTutee(tuteeId, lessonSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}

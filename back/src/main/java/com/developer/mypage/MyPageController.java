package com.developer.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.appliedlesson.service.AppliedLessonService;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.favoriteslesson.dto.FavoritesLessonDTO;
import com.developer.favoriteslesson.service.FavoritesLessonService;
import com.developer.favoritesstudyroom.dto.FavoritesStudyroomDTO;
import com.developer.favoritesstudyroom.service.FavoritesStudyroomService;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.lessonreview.service.LessonReviewService;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.reservation.service.ReservationService;
import com.developer.roomreview.dto.RoomReviewDTO;
import com.developer.roomreview.dto.RoomReviewDTO.RoomReviewInsertDTO;
import com.developer.roomreview.service.RoomReviewService;
import com.developer.userreview.dto.UserReviewDTO;
import com.developer.userreview.service.UserReviewService;
import com.developer.users.dto.UsersDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("mypage/*")
@RequiredArgsConstructor
public class MyPageController {
   
   private final FavoritesStudyroomService fsService;;
   private final FavoritesLessonService flService;
   private final LessonReviewService lrservice;
   private final ReservationService rService;
   private final RoomReviewService rrservice;
   private final AppliedLessonService alService;
   private final UserReviewService urService;
   
   
   /**
    * [FavoritesLesson] 나의 수업 즐겨찾기 목록 확인
    * 
    * @author moonone
    * @param userId 사용자아이디
    * @return 즐겨찾기목록
    * @throws FindException
    */
   @GetMapping(value="favoriteslesson", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> list(HttpSession session) throws FindException {
      String logined = (String) session.getAttribute("logined");
      if (logined != null) {
         List<FavoritesLessonDTO.flListDTO> flDTO = flService.listFavLesson(logined);
         return new ResponseEntity<>(flDTO, HttpStatus.OK);
      }
      return new ResponseEntity<>("로그인하세요", HttpStatus.BAD_REQUEST);
   }

   /**
    * [LessonReview] 후기를 작성하지 않은 수업 목록 확인
    * 
    * @author moonone
    * @param userId 사용자아이디
    * @return 수업목록
    * @throws FindException
    */
   @GetMapping(value = "tutee/lessonreview")
   public ResponseEntity<?> noWriteLReview(HttpSession session) throws FindException {
      String logined = (String) session.getAttribute("logined");
      List<LessonReviewDTO.noWriteLReviewDTO> list = lrservice.noWriteLReview(logined);
      return new ResponseEntity<>(list, HttpStatus.OK);
   }

   /**
    * [LessonReview] 튜터의 수업에 대한 후기 추가 및 수정
    * 
    * @author moonone
    * @param lrDTO 작성한 후기
    * @throws FindException
    */
   @PostMapping(value = "tutee/lessonreview/{applySeq}")
   public ResponseEntity<?> addReview(@RequestBody LessonReviewDTO.lrDTO lrDTO, @PathVariable Long applySeq) throws AddException, FindException {
	   lrservice.addReview(lrDTO, applySeq);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
    * [LessonReview] 작성한 후기 목록 확인
    * 
    * @author moonone
    * @param userId 사용자아이디
    * @return 후기목록
    * @throws FindException
    */
   @GetMapping(value = "tutee/myreview")
   public ResponseEntity<?> lReviewList(HttpSession session) throws FindException {
      String logined = (String) session.getAttribute("logined");
      List<LessonReviewDTO.listLRListDTO> list = lrservice.lReviewList(logined);
      return new ResponseEntity<>(list, HttpStatus.OK);
   }

}

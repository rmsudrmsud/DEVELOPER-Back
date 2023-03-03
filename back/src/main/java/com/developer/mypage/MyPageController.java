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
	 * [FavoritesStudyroom] 나의 스터디카페 즐겨찾기 목록
	 * @author SR
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "favoritesstudyroom", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listFavStudyroom(HttpSession session) throws FindException {

		String userId = (String) session.getAttribute("logined");
		
		List<FavoritesStudyroomDTO.favStudyroomListDTO> list = fsService.listFavStudyroom(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}


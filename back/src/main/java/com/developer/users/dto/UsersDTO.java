package com.developer.users.dto;

import java.util.List;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.board.dto.BoardDTO;
import com.developer.boardrep.dto.BoardRepDTO;
import com.developer.favoriteslesson.entity.FavoritesLesson;
import com.developer.favoritesstudyroom.entity.FavoritesStudyroom;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.recommend.dto.RecommendDTO;
import com.developer.recommend.entity.Recommend;
import com.developer.reservation.entity.Reservation;
import com.developer.tutor.entity.Tutor;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
public class UsersDTO {
	
	@Data
	@NoArgsConstructor
	public static class usersDTO{
		private String userId;
		private String pwd;
		private Integer role;
		private String nickname;
		private String name;
		private String email;
		private String tel;
		private String addr;

		private Tutor tutor;
		private List<AppliedLesson> appliedLesson;
		private List<FavoritesLesson> favoritesLesson;
		private BoardDTO boardDTO;
		private BoardRepDTO boardRepDTO;
		private RecommendDTO recommendDTO;
		private List<Recommend> recommend;
		private List<Reservation> reservation;
		private List<FavoritesStudyroom> favStudyroom;
	}
	//근형 : 로그인
	@Data
	@NoArgsConstructor
	public static class uDTO{
		private String userId;
		private String pwd;
		private Integer role;
		private String nickname;
		private String name;
		private String email;
		private String tel;
		private String addr;
	}
	//근형
	@Data
	@NoArgsConstructor
	public static class UsersNameDTO{
		private String userId;
		private Integer role;
		private String nickname;
	}
	//근형
	@Data
	@NoArgsConstructor
	public static class getNameDTO{
		private String username;
		private LessonDTO.getLessonNameDTO lessonName;
	}
	//근형
	@Data
	@NoArgsConstructor
	public static class getCompletedClassDTO{
		private String username;
		private LessonReviewDTO.getReviewList review;
	}
}

package com.developer.users.dto;
import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.favoritesstudyroom.dto.FavoritesStudyroomDTO;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.tutor.dto.TutorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UsersDTO {
	private String userId;
	private Integer role;
	private String pwd;
	private String nickname;
	private String name;
	private String email;
	private String tel;

	private String addr;	

	private TutorDTO tutorDTO;
	
	private AppliedLessonDTO appliedLessonDTO;
//	private BoardDTO boardDTO;
//	private BoardRepDTO boardRepDTO;
//	private RecommendDTO recommendDTO;
	private ReservationDTO reservationDTO;
	private FavoritesStudyroomDTO favoritesStudyroomDTO;
}

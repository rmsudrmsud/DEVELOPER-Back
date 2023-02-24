package com.developer.users.dto;
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

//	private TutorDTO tDTO;
//	private AppliedLessonDTO alDTO;
//	private BoardDTO bDTO;
//	private BoardRepDTO brDTO;
//	private RecommendDTO rcDTO;
//	private ReservationDTO reDTO;
//	private FavoritesStudyroomDTO favSrDTO;
}

package com.developer.favoritesstudyroom.dto;

import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.entity.Studyroom;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoritesStudyroomDTO {
	private Long favSeq;
	private Users userId;
	private Studyroom Studyroom;
	
	private Integer cnt;
	//ds
	@Data
	@NoArgsConstructor
	public static class favoritesStudyroomUserIdDTO{
		private UsersDTO.UserIdDTO userIdDTO;
		
	}
	
	//ds
	@Data
	@NoArgsConstructor
	public static class fvInsertDTO{
		private Long srSeq;
	}
}

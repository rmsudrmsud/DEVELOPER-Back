package com.developer.favoritesstudyroom.dto;

import com.developer.studyroom.entity.Studyroom;
import com.developer.users.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor	
public class FavoritesStudyroomDTO {
	private Long favSeq;
	private Users userId;
	private Studyroom Studyroom;
	
}

package com.developer.users.dto;

import com.developer.board.dto.BoardDTO;
import com.developer.boardrep.entity.BoardRep;
import com.developer.recommend.dto.RecommendDTO;

import lombok.Data;

@Data
public class UsersDTO {
	private String userId;
	private Integer role;
	private String nickname;
	private String name;
	private String email;
	private String tel;
	private String addr;
	
	private BoardDTO bDTO;
	private BoardRep brDTO;
	private RecommendDTO rDTO;
}

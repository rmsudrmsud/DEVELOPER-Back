package com.developer.users.dto;

import com.developer.board.dto.BoardDTO;
import com.developer.boardrep.dto.BoardRepDTO;
import com.developer.recommend.dto.RecommendDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
public class UsersDTO {
	private String userId;
	private String pwd;
	private Integer role;
	private String nickname;
	private String name;
	private String email;
	private String tel;
	private String addr;

	private BoardDTO boardDTO;
	private BoardRepDTO boardRepDTO;
	private RecommendDTO recommendDTO;

	@Data
	@NoArgsConstructor
	public static class UsersNameDTO{
		private String userId;
		private Integer role;
		private String nickname;
	}
}

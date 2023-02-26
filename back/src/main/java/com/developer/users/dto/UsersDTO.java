package com.developer.users.dto;

import com.developer.board.dto.BoardDTO;
import com.developer.boardrep.entity.BoardRep;
import com.developer.recommend.dto.RecommendDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
	private String userId;
	private Integer role;
	private String nickname;
	private String name;
	private String email;
	private String tel;
	private String addr;

	private BoardDTO boardDTO;
	private BoardRep boardRepDTO;
	private RecommendDTO recommendDTO;

	// SR
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class selectAllBydateBoardDTO {
		private String nickname;
	}
}
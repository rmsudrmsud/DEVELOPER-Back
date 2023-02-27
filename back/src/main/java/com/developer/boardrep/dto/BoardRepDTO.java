package com.developer.boardrep.dto;



import java.util.Date;

import com.developer.board.dto.BoardDTO;
import com.developer.users.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
public class BoardRepDTO {
	private Long postRepSeq;
	private String content;
	private Date cDate;
	private BoardDTO boardDTO;
	private UsersDTO usersDTO;
	
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
	public static class BoardRepSelectDTO{
		private Long postRepSeq;
		private String content;
		private Date cDate;
		private UsersDTO.UsersNameDTO usersNameDTO;
	}
}

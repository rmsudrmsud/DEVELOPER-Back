package com.developer.board.dto;

import java.util.Date;

import com.developer.boardrep.dto.BoardRepDTO;
import com.developer.recommend.dto.RecommendDTO;
import com.developer.users.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private Long postSeq;
	private Integer category;
	private String title;
	private String content;
	private String imgPath;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date cDate;
	private Integer recommend;
	private Integer cnt;

	private BoardRepDTO boardRepDTO;
	private UsersDTO usersDTO;
	private RecommendDTO recommendDTO;

//SR
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class selectAllBydateBoardDTO {
		private Long postSeq;
		private Integer category;
		private String title;
		private String content;
		private String imgPath;
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date cDate;
		private Integer recommend;
		private Integer cnt;
		private UsersDTO.selectAllBydateBoardDTO usersDTO;
	}

}

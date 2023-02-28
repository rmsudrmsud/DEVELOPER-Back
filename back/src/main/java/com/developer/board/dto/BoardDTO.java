package com.developer.board.dto;







import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import com.developer.boardrep.dto.BoardRepDTO;
import com.developer.recommend.dto.RecommendDTO;
import com.developer.users.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@DynamicUpdate
@NoArgsConstructor
@JsonFormat( pattern = "yy-MM-dd", timezone = "Asia/Seoul")
public class BoardDTO {
	private Long postSeq;
	private Integer category;
	private String title;
	private String content;
	private String imgPath;
	private Date cDate;
	private Integer recommend;
	private Integer cnt;
	private BoardRepDTO boardRepDTO;
	private UsersDTO usersDTO;
	private RecommendDTO recommendDTO;
	
	//근형
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	public static class BoardAllSelectDTO{
		private Long postSeq;
		private Integer category;
		private String title;
		private String content;
		private String imgPath;
		private Date cDate;
		private Integer recommend;
		private Integer cnt;
		private BoardRepDTO.BoardRepSelectDTO boardRepSelectDTO;
		private UsersDTO.UsersNameDTO usersNameDTO;
	}
	
	//근형
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	public static class getBoardByBoardTypeDTO{
		private Long postSeq;
		private Integer category;
		private String title;
		private String content;
		private String imgPath;
		private Date cDate;
		private Integer recommend;
		private Integer cnt;
		private UsersDTO.UsersNameDTO usersNameDTO;
	} 
	
	//근형
	@Data
	@NoArgsConstructor
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	public static class saveBoardDTO{
		private Long postSeq;
		private Integer category;
		private String title;
		private String content;
		private String imgPath;
		private UsersDTO.UsersNameDTO usersNameDTO;
	} 
	
}

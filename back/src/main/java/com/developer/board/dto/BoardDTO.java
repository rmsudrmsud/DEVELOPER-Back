package com.developer.board.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.boardrep.dto.BoardRepDTO;
import com.developer.recommend.dto.RecommendDTO;
import com.developer.users.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
	// 근형
	private Long postSeq;
	private Integer category;
	private String title;
	private String content;
	private String imgPath;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDateTime cDate;
	private Integer recommend;
	private Integer cnt;

	private BoardRepDTO boardRepDTO;
	private UsersDTO usersDTO;
	private RecommendDTO recommendDTO;
	private String dateFormat;
	
	public void setcDate(LocalDateTime LocalDateTime) {
	      this.dateFormat = DateTimeFormat.timesAgo(LocalDateTime);
	}
	
	@Data
	@NoArgsConstructor
	public static class BoardAllSelectDTO {
		private Long postSeq;
		private Integer category;
		private String title;
		private String content;
		private String imgPath;
//		@JsonSerialize(using = LocalDateTimeSerializer.class)
//		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date cDate;
		private Integer recommend;
		private Integer cnt;

		// private BoardRepDTO boardRepDTO;
		private BoardRepDTO.BoardRepSelectDTO boardRepSelectDTO;
		private UsersDTO.UsersNameDTO usersNameDTO;
	}

	// SR
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
	


	// 근형
	//댓글 수정폼
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class getBoardByBoardTypeDTO {
		private Long postSeq;
		private Integer category;
		private String title;
		private String content;
		private String imgPath;
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date cDate;
		private Integer recommend;
		private Integer cnt;
		private UsersDTO.UsersNameDTO usersNameDTO;
	}

	// 근형
	@Data
	@NoArgsConstructor
	public static class saveBoardDTO {
		private Long postSeq;
		private Integer category;
		private String title;
		private String content;
		private String imgPath;
		private UsersDTO.UsersNameDTO usersNameDTO;
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private LocalDateTime cDate;
		private Integer recommend;
		private Integer cnt;
		private UsersDTO.selectAllBydateBoardDTO usersDTO;
	}

	// 근형
	@Data
	@NoArgsConstructor
	public static class editBoardDTO {
		private String title;
		private String content;
		private String imgPath;
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//			@ColumnDefault(value = "SYSDATE")
		private LocalDateTime cDate;
	}

//	@Data
//	@NoArgsConstructor
//	public static class PagebeanDTO{
//		private int startPage;
//		private int endPage;
//		private Page<BoardDTO.getBoardByBoardTypeDTO> list;
//	}

}
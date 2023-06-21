package com.developer.boardrep.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.developer.board.dto.BoardDTO;
import com.developer.users.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRepDTO {
	private Long postRepSeq;
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date cDate;
	private BoardDTO bDTO;
	private UsersDTO uDTO;

	// 근형
	@Data
	@NoArgsConstructor
	public static class BoardRepSelectDTO {
		private Long postRepSeq;
		private String content;
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		private Date cDate;
		private UsersDTO.UsersNameDTO usersNameDTO;
	}

	// 근형
	@Data
	@NoArgsConstructor
	public static class saveBoardRepDTO {
		private Long postRepSeq;
		private String content;
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//		@JsonSerialize(using = LocalDateTimeSerializer.class)
//		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		private Date cDate;
		private BoardDTO boardDTO;
		private UsersDTO.UsersNameDTO usersNameDTO;
	}
}
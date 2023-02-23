package com.developer.boardrep.dto;

import java.sql.Date;

import com.developer.board.dto.BoardDTO;
import com.developer.users.dto.UsersDTO;

import lombok.Data;

@Data
public class BoardRepDTO {
	private Long postRepSeq;
	private String content;
	private Date cDate;
	private BoardDTO bDTO;
	private UsersDTO uDTO;
}

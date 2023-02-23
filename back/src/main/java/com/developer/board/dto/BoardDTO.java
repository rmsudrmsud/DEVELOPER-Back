package com.developer.board.dto;

import java.sql.Date;

import com.developer.boardrep.dto.BoardRepDTO;
import com.developer.recommend.dto.RecommendDTO;
import com.developer.users.dto.UsersDTO;

import lombok.Data;

@Data
public class BoardDTO {
	private Long postSeq;
	private Integer category;
	private String title;
	private String content;
	private String imgPath;
	private Date cDate;
	private Integer recommend;
	private Integer cnt;
	
	private BoardRepDTO brDTO;
	private UsersDTO uDTO;
	private RecommendDTO rDTO;
}

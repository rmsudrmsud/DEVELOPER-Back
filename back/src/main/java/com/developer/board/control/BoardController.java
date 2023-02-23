package com.developer.board.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.board.entity.Board;
import com.developer.board.service.BoardService;
import com.developer.exception.AddException;
import com.developer.exception.FindException;

@RestController
@RequestMapping("board/*")
public class BoardController {
	@Autowired
	private BoardService boardservice;
	
	/**
	 * 게시글 작성
	 * @author choigeunhyeong
	 * @param board
	 * @throws AddException
	 */
	@PostMapping(value="add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addBoard(@RequestBody Board board) throws AddException{
		boardservice.addBoard(board);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 글 번호로 게시글 상세 검색(닉네임+글상세+댓글)
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws FindException
	 */
//	private Logger logger = LoggerFactory.getLogger(getClass());
//		@GetMapping(value="findPostSeq" //, produces = MediaType.APPLICATION_JSON_VALUE
//				)
//		public ResponseEntity<?> findPostSeq(@RequestParam Long postSeq) throws FindException{
//			boardservice.findPostSeq(postSeq);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
}

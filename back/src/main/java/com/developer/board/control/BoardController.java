package com.developer.board.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.board.dto.BoardDTO;
import com.developer.board.entity.Board;
import com.developer.board.repository.BoardRepository;
import com.developer.board.service.BoardService;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.ModifyException;
import com.developer.exception.RemoveException;

@RestController
@RequestMapping("board/*")
public class BoardController {
	@Autowired
	private BoardService boardservice;
	/**
	 * 게시판 글 작성
	 * 
	 * @author choigeunhyeong
	 * @param board
	 * @throws AddException
	 */
	
	@PostMapping(value = "add")
	public ResponseEntity<?> addBoard(BoardDTO.saveBoardDTO saveBoardDTO, HttpSession session) throws AddException {
		String logined = (String) session.getAttribute("logined");
		if(logined == null) { //로그인 안한 경우
			throw new AddException("로그인하세요");
		}
		boardservice.addBoard(saveBoardDTO, logined);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 글 번호로 게시글 상세 검색(닉네임+글상세+댓글)
	 * 
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "select/{postSeq}" // , produces = MediaType.APPLICATION_JSON_VALUE
			)
			public ResponseEntity<?> selectAllPostSeq(@PathVariable Long postSeq) throws FindException {
		List<BoardDTO.BoardAllSelectDTO> list= boardservice.selectAllPostSeq(postSeq);
			return new ResponseEntity<>(list, HttpStatus.OK);
				
			}
	/**
	 * 게시글 제목으로 검색
	 * @author choigeunhyeong
	 * @param title
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "search/{title}" // , produces = MediaType.APPLICATION_JSON_VALUE
			)
			public ResponseEntity<?> searchBoard(@PathVariable String title) throws FindException{
			List<Board> list= boardservice.findByTitle("%"+title+"%");
			return new ResponseEntity<>(list, HttpStatus.OK);
				
			}
	
	/**
	 * 글 수정폼.. 
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws FindException
	 * 미완
	 */
	@GetMapping(value = "detail/{postSeq}" // , produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> detailBoard(@PathVariable Long postSeq) throws FindException {
		BoardDTO.getBoardByBoardTypeDTO detail= boardservice.detailBoard(postSeq);
		return new ResponseEntity<>(detail, HttpStatus.OK);
		
	}
	
	/**
	 * 커뮤니티 메인 글목록 (작성일순) boardType 1: 작성일, 2:조회수, 3:추천순
	 * @author choigeunhyeong
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "list/{boardType}" // , produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> getBoardByBoardType(@PathVariable("boardType") Integer boardType) throws FindException {
		List<BoardDTO.getBoardByBoardTypeDTO> list = new ArrayList<>();
		if(boardType == 1) {
		list= boardservice.getBoardByC_date();
		}else if(boardType == 2) {
			list= boardservice.getBoardByCnt();
		}else {
			list= boardservice.getBoardByRecommend();
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	/**
	 * 조회수 증가
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws FindException
	 */
	@PutMapping(value = "updatecnt/{postSeq}" // , produces = MediaType.APPLICATION_JSON_VALUE
			)
			public ResponseEntity<?> searchBoard(@PathVariable Long postSeq) throws ModifyException {
			boardservice.updateCnt(postSeq);
			return new ResponseEntity<>(HttpStatus.OK);
			}
	
	/**
	 * 게시판 글 수정
	 * @author choigeunhyeong
	 * @param board
	 * @param postSeq
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PutMapping(value = "{postSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editBoard(BoardDTO.saveBoardDTO saveBoardDTO, @PathVariable Long postSeq) throws ModifyException {
		boardservice.editBoard(saveBoardDTO, postSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * 게시판 글 삭제
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws RemoveException
	 */
	@DeleteMapping(value = "{postSeq}" // , produces = MediaType.APPLICATION_JSON_VALUE
			)public ResponseEntity<?> deleteBoard(@PathVariable Long postSeq) throws RemoveException {
				boardservice.deleteBoard(postSeq);
				return new ResponseEntity<>(HttpStatus.OK);
		}	
	
}




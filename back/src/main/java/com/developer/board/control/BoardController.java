package com.developer.board.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.developer.board.service.BoardService;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.ModifyException;
import com.developer.exception.RemoveException;



@RestController
@RequestMapping("board/*")
public class BoardController {

	@Autowired
	private BoardService bService;

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
		if (logined == null) { // 로그인 안한 경우
			throw new AddException("로그인하세요");
		}
		bService.addBoard(saveBoardDTO, logined);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 글 수정폼..
	 * 
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws FindException 미완
	 */
	@GetMapping(value = "detail/{postSeq}" // , produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> detailBoard(@PathVariable Long postSeq) throws FindException {
		BoardDTO.getBoardByBoardTypeDTO detail = bService.detailBoard(postSeq);
		return new ResponseEntity<>(detail, HttpStatus.OK);

	}

	/**
	 * 커뮤니티 메인 글목록 (작성일순)
	 * 
	 * @author choigeunhyeong
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "list/{boardType}" // , produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> getBoardByBoardType(@PathVariable("boardType") Integer boardType) throws FindException {
		List<BoardDTO.getBoardByBoardTypeDTO> list = new ArrayList<>();
		if (boardType == 1) {
			list = bService.getBoardByC_date();
		} else if (boardType == 2) {
			list = bService.getBoardByCnt();
		} else {
			list = bService.getBoardByRecommend();
		}
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	/**
	 * 조회수 증가
	 * 
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws FindException
	 */
	@PutMapping(value = "updatecnt/{postSeq}" // , produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> searchBoard(@PathVariable Long postSeq) throws ModifyException {
		bService.updateCnt(postSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 게시판 글 수정
	 * 
	 * @author choigeunhyeong
	 * @param board
	 * @param postSeq
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PutMapping(value = "{postSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editBoard(BoardDTO.saveBoardDTO saveBoardDTO, @PathVariable Long postSeq)
			throws ModifyException {
		bService.editBoard(saveBoardDTO, postSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 게시판 글 삭제
	 * 
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws RemoveException
	 */
	@DeleteMapping(value = "{postSeq}" // , produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> deleteBoard(@PathVariable Long postSeq) throws RemoveException {
		bService.deleteBoard(postSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * [메인페이지] 글작성 최신순으로 list를 출력한다.
	 * 
	 * @author SR
	 * @return ResponseEntity<?>
	 * @throws FindException
	 */
	@GetMapping(value = "listbydate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listBoardByDate() throws FindException {
		List<BoardDTO.selectAllBydateBoardDTO> list = bService.listByDate();
		if (list.isEmpty()) {
			return new ResponseEntity<>("작성된 게시글이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
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
		List<BoardDTO.BoardAllSelectDTO> list = bService.selectAllPostSeq(postSeq);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	/**
	 * 게시글 제목으로 검색
	 * 
	 * @author choigeunhyeong
	 * @param title
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "search/{title}" // , produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> searchBoard(@PathVariable String title) throws FindException {
		List<Board> list = bService.findByTitle("%" + title + "%");
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

}

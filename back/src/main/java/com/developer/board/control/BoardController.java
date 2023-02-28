package com.developer.board.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.board.dto.BoardDTO;
import com.developer.board.service.BoardService;
import com.developer.exception.FindException;

@RestController
@RequestMapping("board/*")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/** [메인페이지] 글작성 최신순으로 list를 출력한다.
	 * @author SR
	 * @return ResponseEntity<?>
	 * @throws FindException
	 */
	@GetMapping(value="listbydate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listBoardByDate() throws FindException {
		List<BoardDTO.selectAllBydateBoardDTO> list = boardService.listByDate();
		if(list.isEmpty()) {
			return new ResponseEntity<>("작성된 게시글이 없습니다.", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}
	
}

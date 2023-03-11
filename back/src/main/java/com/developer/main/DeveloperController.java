package com.developer.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.board.dto.BoardDTO;
import com.developer.board.service.BoardService;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.service.LessonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("main/*")
public class DeveloperController {

	private final BoardService bService;
	private final LessonService lservice;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * [메인페이지] 커뮤니티 + 수업 출력
	 * 
	 * @author SR
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mainPageList() throws FindException {

		DeveloperDTO.MainPageDTO dto = new DeveloperDTO.MainPageDTO();

		List<BoardDTO.selectAllBydateBoardDTO> bList = bService.listByDate();
		List<LessonDTO.selectAllBydateLessonDTO> lList = lservice.selectAllByDateLesson();

		dto.setListBoard(bList);
		dto.setListLesson(lList);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}

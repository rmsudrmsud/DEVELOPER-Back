package com.developer.board.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.board.service.BoardService;

@RestController
@RequestMapping("board/*")
public class BoardController {

	@Autowired
	private BoardService bService;

}

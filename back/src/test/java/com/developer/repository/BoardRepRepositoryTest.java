package com.developer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.board.entity.Board;
import com.developer.boardrep.entity.BoardRep;
import com.developer.boardrep.repository.BoardRepRepository;

@SpringBootTest
class BoardRepRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BoardRepRepository brr;
	
	@Test
	@DisplayName("BoardRep Save 테스트")
	void testBoardRepSave() {
		for(int i=1; i<=3; i++) {
			BoardRep br = new BoardRep();
			br.setPostRepSeq(null);
			br.setContent("4번글의 " + i + "번째 댓글");
			br.setCDate(null);
			br.setPostSeq(4);
			br.setUserId("아이디"+i);
			brr.save(br);
		}
	}
	
	@Test
	@DisplayName("BoardRep FindById 테스트")
	void testBoardRepFindById() {
		BoardRep br = new BoardRep();
		Optional<BoardRep> optB = brr.findById(3L);
		assertTrue(optB.isPresent());
		String expectedId = "아이디1";
		assertEquals(expectedId, optB.get().getUserId());
	}
	
//	
	@Test
	@DisplayName("BoardRep update 테스트")
	void testBoardRepUpdate() {
		
			BoardRep br = new BoardRep();
			br.setPostRepSeq(4L);
			br.setUserId("아이디4");
			brr.save(br);
		
	}
//	
	@Test
	@DisplayName("BoardRep delete 테스트")
	void testBoardRepDelete() {
		Long a = 3L;
		brr.deleteById(a);
	}


}

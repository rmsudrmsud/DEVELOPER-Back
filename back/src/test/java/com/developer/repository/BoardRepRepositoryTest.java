package com.developer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.board.entity.Board;
import com.developer.board.repository.BoardRepository;
import com.developer.boardrep.entity.BoardRep;
import com.developer.boardrep.repository.BoardRepRepository;

@SpringBootTest
class BoardRepRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BoardRepRepository brrp;
	@Autowired
	private BoardRepository brp;
	
//	@Test
//	@DisplayName("BoardRep Save 테스트")
//	void testBoardRepSave() {
//		for(int i=1; i<=5; i++) {
//			BoardRep br = new BoardRep();
//			br.setPostRepSeq(null);
//			br.setContent("1번글의 " + i + "번째 댓글");
//			br.setCDate(null);
//			br.setUserId("아이디"+i);
//			brrp.save(br);
//		}
//	}	
	@Test
	@DisplayName("testCreateBoardRep 테스트")
	void testCreateBoardRep() {
			Board b = new Board();
			b.setPostSeq(null);
			b.setTitle("테스트board");
			b.setContent("테스트 Content");
			b.setUserId("아이디");
			BoardRep br = new BoardRep();
			br.setPostRepSeq(null);
			br.setContent("테스트 댓글");
			br.setCDate(null);
			br.setUserId("아이디");
			br.setBoard(b);
			
			Board board = brp.save(b);
			BoardRep boardrep = brrp.save(br);
			
			assertNotNull(boardrep.getPostRepSeq());
	        assertEquals(boardrep.getContent(), "테스트 댓글");
	        assertEquals(boardrep.getBoard(), board);
				
	}
	
	@Test
	@DisplayName("BoardRep FindById 테스트")
	void testBoardRepFindById() {
		BoardRep br = new BoardRep();
		Optional<BoardRep> optB = brrp.findById(1L);
		assertTrue(optB.isPresent());
		String expectedId = "아이디1";
		assertEquals(expectedId, optB.get().getUserId());
	}
	
//	
	@Test
	@DisplayName("BoardRep update 테스트")
	void testBoardRepUpdate() {
		
			BoardRep br = new BoardRep();
			br.setPostRepSeq(1L);
			br.setContent("boardrep수정테스트");
			br.setUserId("아이디2");
			br.setCDate(null);
			brrp.save(br);
		
	}
//	
	@Test
	@DisplayName("BoardRep delete 테스트")
	void testBoardRepDelete() {
		Long a = 3L;
		brrp.deleteById(a);
	}


}

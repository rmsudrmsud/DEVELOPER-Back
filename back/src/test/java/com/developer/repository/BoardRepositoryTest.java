package com.developer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.board.entity.Board;
import com.developer.board.repository.BoardRepository;

@SpringBootTest
class BoardRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BoardRepository br;
	
	@Test
	@DisplayName("Board Save 테스트")
	void testBoardSave() {
		for(int i=1; i<=5; i++) {
			Board b = new Board();
			b.setPostSeq(null);
			b.setUserId("아이디"+i);
			b.setCategory(i);
			b.setTitle("JPA테스트제목"+i);
			b.setContent("JPA테스트내용"+i);
			b.setImgPath("이미지"+i);
		//	b.setCDate(null);
//			b.setRecommend(i);
//			b.setCnt(i);
			br.save(b);
		}
	}
	
	@Test
	@DisplayName("Board FindById 테스트")
	void testBoardFindById() {
		Board b = new Board();
		Optional<Board> optB = br.findById(1L);
		assertTrue(optB.isPresent());
		String expectedId = "아이디1";
		assertEquals(expectedId, optB.get().getUserId());
	}
	
	@Test
	@DisplayName("Board update 테스트")
	void testBoardUpdate() {
		
			Board b = new Board();
			b.setPostSeq(1L);
			b.setUserId("아이디4");
			b.setRecommend(0);
			b.setCnt(0);
			br.save(b);
		
	}
	
	@Test
	@DisplayName("Board delete 테스트")
	void testBoardDelete() {
		Long a = 2L;
		br.deleteById(a);
	}
	
	@Test
	void testFindTest1() {
		List<Board> list = br.findTest1();
		logger.error("게시글:" + list);
	}
	
	@Test
	@DisplayName("Board list 테스트")
	void findBoardList() {
	List<Object[]> list = br.findTest2();
	logger.error("첫번째글번호:" + list.get(0)[0]);
	logger.error("첫번째내용:" + list.get(0)[1]);
	
	logger.error("두번째글번호:" + list.get(1)[0]);
	logger.error("두번쨰내용:" + list.get(1)[1]);
	}
}

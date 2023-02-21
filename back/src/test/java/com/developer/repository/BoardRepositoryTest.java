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
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@SpringBootTest
class BoardRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BoardRepository br;
	@Autowired
	private UsersRepository ur;
	
	@Test
	@DisplayName("Board Save 테스트")
	void testBoardSave() {
		for(int i=1; i<=5; i++) {
			Board b = new Board();
			Optional<Users> optB = ur.findById("아이디1");
			Users u = optB.get();
			b.setUsers(u);
			b.setPostSeq(null);
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
		String testId = optB.get().getUsers().getUserId();
		assertEquals(expectedId, testId);
	}
	
	@Test
	@DisplayName("Board update 테스트")
	void testBoardUpdate() {
		
			Board b = new Board();
			b.setPostSeq(5L);
	//		b.setUserId("아이디1");
			Optional<Users> optB = ur.findById("아이디3");
			Users u = optB.get();
			b.setUsers(u);
			b.setTitle("JPA테스트제목수정");
			b.setContent("JPA테스트내용수정");
			b.setRecommend(1);
			b.setCnt(1);
			br.save(b);	
	}
	
	@Test
	@DisplayName("Board delete 테스트")
	void testBoardDelete() {
		Long postSeq = 3L;
		br.deleteById(postSeq);
	}
	
	@Test
	void testFindTest1() {
		List<Board> list = br.findTest1();
		for(int i=0; i<list.size(); i++) {
			logger.error("게시글: " + list);
		};
	}
	
	@Test
	@DisplayName("Board list 테스트")
	void findFindTest2() {
	List<Object[]> list = br.findTest2();
	logger.error("첫번째글번호: " + list.get(0)[0]);
	logger.error("첫번째내용: " + list.get(0)[1]);
	
	logger.error("두번째글번호: " + list.get(1)[0]);
	logger.error("두번쨰내용: " + list.get(1)[1]);
	}
	
	@Test
	@DisplayName("Board 제목검색 테스트")
	void findByTitle() {
		List<Board> list = br.findByTitleLike("%수정%");
		for(int i=0; i<list.size(); i++) {
			logger.info("검색내용: "+list.get(i).getTitle());
		};
	}
	
	@Test
	@DisplayName("Board 셀렉트 PostSeq 테스트")
	void findPostSeq() {
		Object board = br.findPostSeq(1L);
		logger.info(board.getClass().toString());
	}
}

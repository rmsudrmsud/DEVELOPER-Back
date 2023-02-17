package com.developer.repository;

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
	void testSave() {
			Board b = new Board();
			b.setPostSeq(32L);
			b.setUserId("kosta111");
			b.setTitle("JPA테스트제목");
			b.setContent("JPA테스트내용");
			b.setImgPath("테스트이미지");
			b.setCDate(null);
			b.setCategory(2);
			b.setRecommend(15);
			b.setCnt(15);
		
			br.save(b);
	}
	
	@Test
	void testFind() {
			Board b = new Board();
			b.setPostSeq(32L);
			b.setUserId("kosta111");
			b.setTitle("JPA테스트제목");
			b.setContent("JPA테스트내용");
			b.setImgPath("테스트이미지");
			b.setCDate(null);
			b.setCategory(2);
			b.setRecommend(15);
			b.setCnt(15);
		
			br.save(b);
	}

}

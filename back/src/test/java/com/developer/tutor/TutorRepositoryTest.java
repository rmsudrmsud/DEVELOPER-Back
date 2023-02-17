package com.developer.tutor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;

@SpringBootTest
public class TutorRepositoryTest {
	@Autowired
	private TutorRepository tRepository;
	
	@Test
	@DisplayName("tutorAdd() 테스트")
	void testTutorAdd() {
		Tutor t = new Tutor();
		t.setUserId("devman");
		t.setInfo("테스트입니다.");
		t.setImgPath("...");
		t.setStarAvg(5);
		t.setApplyOk(1);
		tRepository.save(t);
	}

}

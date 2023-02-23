package com.developer.studyroom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.repository.StudyroomRepository;
@SpringBootTest
class StudyroomRepositoryTest {
	@Autowired
	private StudyroomRepository sr;
	
	@Test
	void testAdd() {
		Studyroom st = new Studyroom();
		st.setAddr("부산");
		st.setOpenTime("08:00");
		st.setEndTime("23:00");
		st.setHostId("suho522");
		st.setInfo("아아");
		st.setOc(1);
		st.setName("부산스터디룸");
		sr.save(st);
	}

}

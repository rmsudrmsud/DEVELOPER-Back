package com.developer.RoomInfoRepository;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.roominfo.entity.RoomInfo;
import com.developer.roominfo.repository.RoomInfoRepository;
import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.repository.StudyroomRepository;
@SpringBootTest
class RoomInfoRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	private RoomInfoRepository rre;
	
	@Autowired
	private StudyroomRepository sr;
	
	@DisplayName("룸인포 추가 기능 테스트")
	@Test
	void testRoomINfoAdd() {
		RoomInfo ri = new RoomInfo();
		ri.setName("5인실");
		ri.setInfo("5인실입니다");
		ri.setPerson(5);
		ri.setPrice(5000);
		Optional<Studyroom> optS = sr.findById(1L);
		Studyroom s = optS.get();
		ri.setSrSeq(s.getSrSeq());
		rre.save(ri);
		
		
	}

}

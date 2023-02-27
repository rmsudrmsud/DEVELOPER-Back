package com.developer.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.tutor.entity.Tutor;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@SpringBootTest
public class UsersRepositoryTest {
	@Autowired
	private UsersRepository uRepository;
	
	@Test
	void test() {
		
	}
	
	@Test
	@DisplayName("유저 INSERT 테스트")
	void testUsersAdd() {
		Users u = new Users();
		u.setUserId("tutee2");
		u.setRole(1);
		u.setPwd("4테스트비밀번호");
		u.setNickname("4테스트닉네임");
		u.setName("4테스트이름");
		u.setEmail("4테스트이메일");
		u.setTel("4테스트전화번호");
		u.setAddr("4테스트주소");
		uRepository.save(u);
	}
	
	@Test
	@DisplayName("유저 SELECT 테스트")
	void testFind() {
		Optional<Users> optU = uRepository.findByUserId("test1");
		assertTrue(optU.isPresent());
		String expected = "test";
		assertEquals(expected, optU.get().getUserId());
	}
	
	@Test
	@DisplayName("유저 UPDATE 테스트")
	void testUpdate() {
		Optional<Users> optU = uRepository.findByUserId("test1");
		assertTrue(optU.isPresent());
		Users u = optU.get();
		u.setRole(2);		
		uRepository.save(u);
	}
	
	@Test
	@DisplayName("유저 DELETE 테스트")
	void testDelete() {
		Optional<Users> optU = uRepository.findByUserId("test");
		Users u = optU.get();
//		u.setTutor(null);
		uRepository.delete(u);
	}


}

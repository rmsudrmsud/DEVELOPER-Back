package com.developer.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.users.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
	// TUTOR + USERS 동시 출력
	@EntityGraph(attributePaths = "tutor")
	public Optional<Users> findByUserId(String userId);

	// [SR] 미승인 튜터 목록
	@Query(value = "SELECT u.user_id, u.name, u.nickname, u.email, u.tel "
					+ "	FROM users u, tutor t "
					+ "	WHERE u.user_id = t.user_id " 
					+ "	AND apply_ok = 0", nativeQuery = true)
	public List<Object[]> selectAllUnapproveTutor();
}

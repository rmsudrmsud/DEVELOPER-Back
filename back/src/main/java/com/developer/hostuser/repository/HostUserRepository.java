package com.developer.hostuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.developer.hostuser.entity.HostUser;
import com.developer.users.entity.Users;

public interface HostUserRepository extends CrudRepository<HostUser, String> {

	// [JH] 호스트유저 전체 목록
	@Query(value = " SELECT *" 
			+ " FROM HOST_USER" 
			+ " order by name", nativeQuery = true)
	public List<HostUser> selectAll();

	// [SR] 미승인 호스트유저 목록
	@Query(value = "SELECT host_id, num, name, tel, email " 
			+ "FROM host_user " 
			+ "WHERE ready = 0", nativeQuery = true)
	public List<Object[]> selectAllUnapproveHost();
	
	//[SR] 호스트 비밀번호찾기용
		@Query(value="select * from host_user where email = :email",nativeQuery= true)
		public HostUser hostPwdAndEmailCheck(@Param("email") String email);

}

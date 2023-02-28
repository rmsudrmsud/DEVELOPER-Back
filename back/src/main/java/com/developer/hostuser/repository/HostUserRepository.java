package com.developer.hostuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.developer.hostuser.entity.HostUser;

public interface HostUserRepository extends CrudRepository<HostUser, String> {

	//[SR] 미승인 호스트유저 목록 
	  @Query(value="SELECT host_id, num, name, tel, email "
	  		+ "FROM host_user "
	  		+ "WHERE ready = 0", nativeQuery = true)
		public List<Object[]> selectAllUnapproveHost();
}

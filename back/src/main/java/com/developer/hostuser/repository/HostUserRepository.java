package com.developer.hostuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;

public interface HostUserRepository extends CrudRepository<HostUser, String> {

	@Query(value=" SELECT *"
			+ " FROM HOST_USER"
			+ " order by name", nativeQuery = true)
	public List<HostUser> selectAll();
}

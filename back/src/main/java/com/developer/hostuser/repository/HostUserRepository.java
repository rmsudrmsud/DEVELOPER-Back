package com.developer.hostuser.repository;

import org.springframework.data.repository.CrudRepository;

import com.developer.hostuser.entity.HostUser;

public interface HostUserRepository extends CrudRepository<HostUser, String> {
	
}

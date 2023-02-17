package com.developer.repository;

import org.springframework.data.repository.CrudRepository;

import com.developer.users.entity.Users;

public interface UsersRepository extends CrudRepository<Users, String> {

}

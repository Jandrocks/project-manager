package com.alemeza.projectmanager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alemeza.projectmanager.models.User;

public interface UserRepo extends CrudRepository<User, Long>{

	
	User findByEmail(String email);
}
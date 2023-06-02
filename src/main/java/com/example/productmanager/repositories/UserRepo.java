package com.example.productmanager.repositories;


import org.springframework.data.repository.CrudRepository;

import com.example.productmanager.models.User;

public interface UserRepo extends CrudRepository<User, Long>{

	
	User findByEmail(String email);
}

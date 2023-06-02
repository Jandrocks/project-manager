package com.example.productmanager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.productmanager.models.Task;

public interface TaskRepo extends CrudRepository<Task, Long>{

}

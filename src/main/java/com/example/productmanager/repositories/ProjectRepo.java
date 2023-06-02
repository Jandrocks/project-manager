package com.example.productmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.productmanager.models.Project;
import com.example.productmanager.models.User;

public interface ProjectRepo extends CrudRepository<Project, Long>{

	//Todos los proyectos, no importa de quien
	@Query(value = "SELECT * FROM projects WHERE user_id != :id", nativeQuery = true)
    List<Project> findAll(Long id);
	
	//Todos los proyectos asociados a un usuario especifico
	List<Project> findAllByLider(User usuario);
	
	//
	List<Project> findByCompasNotContains(User usuario);
	
	List<Project> findByCompasOrLider(User usuario1, User usuario2);
}

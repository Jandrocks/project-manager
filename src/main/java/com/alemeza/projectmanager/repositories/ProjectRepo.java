package com.alemeza.projectmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alemeza.projectmanager.models.Project;
import com.alemeza.projectmanager.models.User;

public interface ProjectRepo extends CrudRepository<Project, Long>{

	//Todos los proyectos, no importa de quien
	@Query(value = "SELECT * FROM projects WHERE user_id != :id", nativeQuery = true)
    List<Project> findAll(Long id);
	
	//Todos los proyectos asociados a un usuario especifico
	List<Project> findAllByLider(User usuario);
	
}
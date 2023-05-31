package com.alemeza.projectmanager.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alemeza.projectmanager.models.Project;
import com.alemeza.projectmanager.models.User;
import com.alemeza.projectmanager.repositories.ProjectRepo;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepo projectRepo;

	public List<Project> todosLosProyectos(Long idUsuario) {
		return projectRepo.findAll(idUsuario);
	}

	public List<Project> proyectosLider(User usuario) {
		return projectRepo.findAllByLider(usuario);
	}

	public Project crearProyecto(Project proyecto) {
		return projectRepo.save(proyecto);
	}

	public Project findProjectById(Long idProyecto) {
		return projectRepo.findById(idProyecto).orElse(null);

	}
}

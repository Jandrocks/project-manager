package com.example.productmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productmanager.models.Project;
import com.example.productmanager.models.User;
import com.example.productmanager.repositories.ProjectRepo;

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
	
	public List<Project> getUsuariosNoAsignados(User usuario){
		return projectRepo.findByCompasNotContains(usuario);
	}
	
	public List<Project> getUsuariosAsignados(User usuario1, User usuario2){
		return projectRepo.findByCompasOrLider(usuario1, usuario2);
	}
	
	public void unirseEquipo(Project pro, 
			User usuario, boolean join) {
//		System.out.println(evento.getNombreEvento() + usuario.getNombre() + " aquiiiii ");
		if(join) {
			pro.getCompas().add(usuario);
		}else {
			pro.getCompas().remove(usuario);
		}
		projectRepo.save(pro);
	}
	
	public void deleteProject(Long id) {
		projectRepo.deleteById(id);
		
	}
}

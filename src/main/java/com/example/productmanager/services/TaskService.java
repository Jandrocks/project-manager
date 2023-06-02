package com.example.productmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productmanager.models.Project;
import com.example.productmanager.models.Task;
import com.example.productmanager.models.User;
import com.example.productmanager.repositories.TaskRepo;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepo taskRepo;
	
	public void crearTarea(String task, User u, Project project) {
		Task tarea = new Task(task,u, project);
		taskRepo.save(tarea);
	}

}

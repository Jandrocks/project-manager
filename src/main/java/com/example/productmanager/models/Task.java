package com.example.productmanager.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Por favor ingresa una tarea")
	private String tarea;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	//TABLA INTEMEDIA DE MENSAJERIA(MUCHOS A MUCHOS) ENTRE AUTORES Y EVENTOS
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		private User autor;
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "project_id")
		private Project project;
		
		
		// CONSTRUCTORES
		public Task() {
			
		}
		
		public Task(String tarea,User autor, Project project) {
			this.tarea = tarea;
			this.autor = autor;
			this.project = project;
		}
		
		//GETTTERS Y SETTERS
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTask() {
			return tarea;
		}
		public void setTask(String tarea) {
			this.tarea = tarea;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		public User getAutor() {
			return autor;
		}
		public void setAutor(User autor) {
			this.autor = autor;
		}
		public Project getProject() {
			return project;
		}
		public void setProject(Project project) {
			this.project = project;
		}
		
		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}

		@PreUpdate
		protected void onUpdate() {
			this.updatedAt = new Date();
		}
		
		

}

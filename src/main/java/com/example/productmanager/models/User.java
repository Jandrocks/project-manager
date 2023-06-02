package com.example.productmanager.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Por favor ingresa un nombre de usuario")
	@Size(min = 3, max = 30, message = "Nombre debe contener entre 3 y 30 caracteres")
	private String nombre;

	@NotBlank(message = "Por favor ingresa un nombre de usuario")
	@Size(min = 3, max = 30, message = "Apellido debe contener entre 3 y 30 caracteres")
	private String apellido;

	@Email(message = "El correo ingresado no es valido")
	@NotBlank(message = "Por favor, no olivdes ingresar un correo electronico")
	private String email;

	@NotBlank(message = "Por favor, ingresa el password")
	@Size(min = 8, max = 64, message = "Password debe contener entre 8 a 20 caracteres ")
	private String password;

	@Transient
	@NotBlank(message = "Confirma la contraseña por favor")
	private String passwordConfirmation;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	// Relacion 1:n a Eventos
	@OneToMany(mappedBy = "lider", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Project> proyectosLider;

	// Relacion muchos a muchos hacia TASKS
	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
	private List<Task> tasks;

	// Relacion muchos a muchos de Usuarios y Eventos
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "colaboradores", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> proyectosInteres;

	// Constructors
	public User() {

	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Project> getProyectosInteres() {
		return proyectosInteres;
	}

	public void setProyectosInteres(List<Project> proyectosInteres) {
		this.proyectosInteres = proyectosInteres;
	}

	public List<Project> getProyectosLider() {
		return proyectosLider;
	}

	public void setProyectosLider(List<Project> proyectosLider) {
		this.proyectosLider = proyectosLider;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}

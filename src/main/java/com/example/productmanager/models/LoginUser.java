package com.example.productmanager.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



public class LoginUser {

	@Email(message = "El correo ingresado no es valido")
	@NotBlank(message = "Por favor, ingresa el correo")
	private String email;

	@NotBlank(message = "Password no valido")
	private String password;
	
	
	public LoginUser() {
		
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
	
}

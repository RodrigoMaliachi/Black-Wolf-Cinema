package com.uady.blackWolfCinema.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserValidation {

	@NotBlank(message = "El nombre de usuario no puede estar vacío")
	@Size(min = 4, message = "El nombre de usuario debe tener al menos 4 caracteres")
	private String userName;

	@ValidPassword
	private String password;

	@NotBlank(message = "El nombre no puede estar vacío")
	@ValidNames
	private String firstName;

	@NotBlank(message = "El apellido no puede estar vacío")
	@ValidNames
	private String lastName;

	@NotBlank(message = "El correo electrónico no puede estar vacío")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "El correo electrónico no es válido")
	@UniqueEmail
	private String email;

	public UserValidation() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

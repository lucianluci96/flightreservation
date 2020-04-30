package com.lucian.flightreservation.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lucian.flightreservation.dto.UserDto;

@Entity
public class User extends AbstractEntity {
	
	@NotNull(message = "First Name is required")
	private String firstName;
	
	@NotNull(message = "Last Name is required")
	private String lastName;
	
	@NotNull(message = "Email is required")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@Email
	private String email;
	
	@NotNull(message = "Password is required")
	private String password;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ "]";
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public UserDto toDto() {
		return new UserDto(this.getId(), this.getFirstName(), this.getLastName(), this.getEmail(), this.getPassword());
	}

}

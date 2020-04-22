package com.lucian.flightreservation.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lucian.flightreservation.dto.PassengerDto;

@Entity
public class Passenger extends AbstractEntity {

	@NotNull(message = "First Name is required")
	private String firstName;

	@NotNull(message = "Last Name is required")
	private String lastName;
	
	private String middleName;

	@NotNull(message = "Email is required")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@Email
	private String email;

	@NotNull(message = "Phone is required")
	private String phone;

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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Passenger [firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", email=" + email + ", phone=" + phone + "]";
	}

	public PassengerDto toDto() {
		return new PassengerDto(this.getId(), this.getFirstName(), this.getLastName(), this.getMiddleName(),
				this.getEmail(), this.getPhone());
	}

}

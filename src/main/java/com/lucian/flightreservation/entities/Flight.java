package com.lucian.flightreservation.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.lucian.flightreservation.dto.FlightDto;

@Entity
public class Flight extends AbstractEntity {

	@NotNull(message = "Flight Number is required")
	private String flightNumber;
	
	@NotNull(message = "Operating Airlines is required")
	private String operatingAirlines;
	
	@NotNull(message = "Departure City is required")
	private String departureCity;
	
	@NotNull(message = "Arrival City is required")
	private String arrivalCity;
	
	@NotNull(message = "Date of Departure is required")
	private Date dateOfDeparture;
	
	@NotNull(message = "Estimated Departure Time is required")
	private Timestamp estimatedDepartureTime;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOperatingAirlines() {
		return operatingAirlines;
	}

	public void setOperatingAirlines(String operatingAirlines) {
		this.operatingAirlines = operatingAirlines;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Date getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(Date dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public Timestamp getEstimatedDepartureTime() {
		return estimatedDepartureTime;
	}

	public void setEstimatedDepartureTime(Timestamp estimatedDepartureTime) {
		this.estimatedDepartureTime = estimatedDepartureTime;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", operatingAirlines=" + operatingAirlines + ", departureCity="
				+ departureCity + ", arrivalCity=" + arrivalCity + ", dateOfDeparture=" + dateOfDeparture
				+ ", estimatedDepartureTime=" + estimatedDepartureTime + "]";
	}

	public FlightDto toDto() {
		return new FlightDto(this.getId(), this.getFlightNumber(), this.getOperatingAirlines(), this.getDepartureCity(),
				this.getArrivalCity(), this.getDateOfDeparture(), this.getEstimatedDepartureTime());
	}

}

package com.lucian.flightreservation.dto;

import javax.validation.constraints.Min;

public class ReservationUpdateRequest {

	private Long id;
	private Boolean checkedIn;
	
	@Min(value=1,message = "Number of bags must be at least 1")
	private int numberOfBags;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public int getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

}

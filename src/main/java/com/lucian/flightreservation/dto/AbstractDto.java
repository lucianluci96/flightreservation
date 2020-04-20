package com.lucian.flightreservation.dto;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractDto {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

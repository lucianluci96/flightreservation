package com.lucian.flightreservation.service;

import java.util.List;

import com.lucian.flightreservation.dto.ReservationDto;
import com.lucian.flightreservation.dto.ReservationRequest;
import com.lucian.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
	
	List<ReservationDto> getReservations();

	Reservation getReservation(long id);

	void deleteReservation(long id);
}

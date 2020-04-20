package com.lucian.flightreservation.service;

import com.lucian.flightreservation.dto.ReservationUpdateRequest;
import com.lucian.flightreservation.entities.Reservation;

public interface CheckInService {

	public Reservation findReservation(Long id);

	public Reservation checkInReservation(ReservationUpdateRequest request);
}
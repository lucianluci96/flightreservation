package com.lucian.flightreservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucian.flightreservation.dto.ReservationUpdateRequest;
import com.lucian.flightreservation.entities.Reservation;
import com.lucian.flightreservation.repository.ReservationRepository;
import com.lucian.flightreservation.service.CheckInService;
import com.lucian.flightreservation.service.ReservationService;

@Component
public class CheckInServiceImpl implements CheckInService {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Reservation findReservation(Long id) {
		Reservation reservation = reservationService.getReservation(id);

		return reservation;
	}

	@Override
	public Reservation checkInReservation(ReservationUpdateRequest request) {

		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(true);
		Reservation updatedReservation = reservationRepository.save(reservation);

		return updatedReservation;
	}

}

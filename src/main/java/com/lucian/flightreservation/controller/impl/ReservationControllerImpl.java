package com.lucian.flightreservation.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucian.flightreservation.dto.ReservationDto;
import com.lucian.flightreservation.dto.ReservationRequest;
import com.lucian.flightreservation.entities.Reservation;
import com.lucian.flightreservation.repository.FlightRepository;
import com.lucian.flightreservation.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationControllerImpl {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	ReservationService reservationService;

	@GetMapping
	public ResponseEntity<List<ReservationDto>> getReservations() {
		return ResponseEntity.ok(reservationService.getReservations());

	}

	@GetMapping("/{id}")
	public ResponseEntity<ReservationDto> getReservation(@PathVariable long id) {
		Reservation reservation = reservationService.getReservation(id);

		if (reservation == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return new ResponseEntity<ReservationDto>(reservation.toDto(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ReservationDto> deleteReservation(@PathVariable long id) {
		Reservation reservation = reservationService.getReservation(id);

		if (reservation == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		reservationService.deleteReservation(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

	}

	@PostMapping
	public ResponseEntity<ReservationDto> completeReservation(@RequestBody ReservationRequest request) {
		return ResponseEntity.ok(reservationService.bookFlight(request).toDto());
	}
}

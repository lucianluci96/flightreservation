package com.lucian.flightreservation.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		return ResponseEntity.ok(reservationService.getReservation(id).toDto());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ReservationDto> deleteReservation(@PathVariable long id) {
		reservationService.deleteReservation(id);

		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<ReservationDto> completeReservation(@RequestBody ReservationRequest request) {
		return ResponseEntity.ok(reservationService.bookFlight(request).toDto());
	}
}

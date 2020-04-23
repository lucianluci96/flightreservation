package com.lucian.flightreservation.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucian.flightreservation.dto.ReservationDto;
import com.lucian.flightreservation.dto.ReservationRequest;
import com.lucian.flightreservation.entities.Reservation;
import com.lucian.flightreservation.repository.FlightRepository;
import com.lucian.flightreservation.service.ReservationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reservations")
@Api(value = "Reservation Management System", description = "Reservation Management System")
public class ReservationControllerImpl {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	ReservationService reservationService;

	@ApiOperation(value = "View a list with available reservations")
	@GetMapping
	public ResponseEntity<List<ReservationDto>> getReservations() {
		return ResponseEntity.ok(reservationService.getReservations());

	}

	@ApiOperation(value = "Get reservation by ID")
	@GetMapping("/{id}")
	public ResponseEntity<ReservationDto> getReservation(@PathVariable long id) {
		Reservation reservation = reservationService.getReservation(id);

		if (reservation == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return new ResponseEntity<ReservationDto>(reservation.toDto(), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete reservation")
	@DeleteMapping("/{id}")
	public ResponseEntity<ReservationDto> deleteReservation(@PathVariable long id) {
		Reservation reservation = reservationService.getReservation(id);

		if (reservation == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		reservationService.deleteReservation(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

	}

	@ApiOperation(value = "Create reservation")
	@PostMapping
	public ResponseEntity<ReservationDto> completeReservation(@RequestBody @Valid ReservationRequest request) {
		return ResponseEntity.ok(reservationService.bookFlight(request).toDto());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;
	}
}

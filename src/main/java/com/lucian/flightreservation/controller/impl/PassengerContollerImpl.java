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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucian.flightreservation.dto.PassengerDto;
import com.lucian.flightreservation.entities.Passenger;
import com.lucian.flightreservation.service.PassengerService;

@RestController
@RequestMapping("/passengers")
public class PassengerContollerImpl {

	@Autowired
	private PassengerService passengerService;

	@GetMapping
	public ResponseEntity<List<PassengerDto>> getPassengers() {
		return ResponseEntity.ok(passengerService.getPassengers());

	}

	@GetMapping("/{id}")
	public ResponseEntity<PassengerDto> getPassenger(@PathVariable long id) {
		Passenger passenger = passengerService.getPassenger(id);

		if (passenger == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return new ResponseEntity<PassengerDto>(passenger.toDto(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<PassengerDto> updatePassenger(@RequestBody @Valid Passenger passenger) {
		passengerService.updatePassenger(passenger);
		return new ResponseEntity<PassengerDto>(passenger.toDto(), HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<PassengerDto> addPassenger(@RequestBody @Valid Passenger passenger) {
		passengerService.addPassenger(passenger);
		return new ResponseEntity<PassengerDto>(passenger.toDto(), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PassengerDto> deletePassenger(@PathVariable long id) {
		Passenger passenger = passengerService.getPassenger(id);

		if (passenger == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		passengerService.deletePassenger(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

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

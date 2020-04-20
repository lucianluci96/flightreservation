package com.lucian.flightreservation.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return ResponseEntity.ok(passengerService.getPassenger(id).toDto());
	}

	@PutMapping
	public ResponseEntity<PassengerDto> updatePassenger(@RequestBody Passenger passenger) {
		return ResponseEntity.ok(passengerService.updatePassenger(passenger).toDto());
	}

	@PostMapping
	public ResponseEntity<PassengerDto> addPassenger(@RequestBody Passenger passenger) {
		return ResponseEntity.ok(passengerService.addPassenger(passenger).toDto());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PassengerDto> deletePassenger(@PathVariable long id) {
		passengerService.deletePassenger(id);

		return ResponseEntity.ok().build();
	}

}

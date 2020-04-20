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

import com.lucian.flightreservation.dto.FlightDto;
import com.lucian.flightreservation.entities.Flight;
import com.lucian.flightreservation.service.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightControllerImpl {

	@Autowired
	private FlightService flightService;

	@GetMapping
	public ResponseEntity<List<FlightDto>> getFlights() {
		return ResponseEntity.ok(flightService.getFlights());

	}

	@GetMapping("/from={departureCity}&to={arrivalCity}")
	public ResponseEntity<List<FlightDto>> getFlightsFrom(@PathVariable String arrivalCity,
			@PathVariable String departureCity) {
		return ResponseEntity.ok(flightService.getFlightsFromTo(arrivalCity, departureCity));

	}

	@GetMapping("/from={departureCity}")
	public ResponseEntity<List<FlightDto>> getFlightsFrom(@PathVariable String departureCity) {
		return ResponseEntity.ok(flightService.getFlightsFrom(departureCity));

	}

	@GetMapping("/to={arrivalCity}")
	public ResponseEntity<List<FlightDto>> getFlightsTo(@PathVariable String arrivalCity) {
		return ResponseEntity.ok(flightService.getFlightsTo(arrivalCity));

	}

	@GetMapping("/{id}")
	public ResponseEntity<FlightDto> getFlight(@PathVariable long id) {
		return ResponseEntity.ok(flightService.getFlight(id).toDto());
	}

	@PutMapping
	public ResponseEntity<FlightDto> updateFlight(@RequestBody Flight flight) {
		return ResponseEntity.ok(flightService.updateFlight(flight).toDto());
	}

	@PostMapping
	public ResponseEntity<FlightDto> addFlight(@RequestBody Flight flight) {
		return ResponseEntity.ok(flightService.addFlight(flight).toDto());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<FlightDto> deleteFlight(@PathVariable long id) {
		flightService.deleteFlight(id);

		return ResponseEntity.ok().build();
	}

}

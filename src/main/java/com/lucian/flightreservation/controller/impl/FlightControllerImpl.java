package com.lucian.flightreservation.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		Flight flight = flightService.getFlight(id);

		if (flight == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return new ResponseEntity<FlightDto>(flight.toDto(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<FlightDto> updateFlight(@RequestBody Flight flight) {

		if (flight == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

		flightService.updateFlight(flight);
		return new ResponseEntity<FlightDto>(flight.toDto(), HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<FlightDto> addFlight(@RequestBody Flight flight) {

		if (flight == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

		flightService.addFlight(flight);
		return new ResponseEntity<FlightDto>(flight.toDto(), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<FlightDto> deleteFlight(@PathVariable long id) {
		Flight flight = flightService.getFlight(id);

		if (flight == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		flightService.deleteFlight(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

}

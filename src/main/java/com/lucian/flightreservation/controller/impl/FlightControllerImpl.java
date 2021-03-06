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

import com.lucian.flightreservation.dto.FlightDto;
import com.lucian.flightreservation.entities.Flight;
import com.lucian.flightreservation.service.FlightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/flights")
@Api(value = "Flight Management System", description = "Flight Management System")
public class FlightControllerImpl {

	@Autowired
	private FlightService flightService;

	@ApiOperation(value = "View a list of available flights")
	@GetMapping
	public ResponseEntity<List<FlightDto>> getFlights() {
		return ResponseEntity.ok(flightService.getFlights());

	}

	@ApiOperation(value = "View a list of available flights from a specific city to another")
	@GetMapping("/from={departureCity}&to={arrivalCity}")
	public ResponseEntity<List<FlightDto>> getFlightsFrom(@PathVariable String arrivalCity,
			@PathVariable String departureCity) {
		return ResponseEntity.ok(flightService.getFlightsFromTo(arrivalCity, departureCity));

	}

	@ApiOperation(value = "View a list of available flights from a specific city")
	@GetMapping("/from={departureCity}")
	public ResponseEntity<List<FlightDto>> getFlightsFrom(@PathVariable String departureCity) {
		return ResponseEntity.ok(flightService.getFlightsFrom(departureCity));

	}

	@ApiOperation(value = "View a list of available flights to a specific city")
	@GetMapping("/to={arrivalCity}")
	public ResponseEntity<List<FlightDto>> getFlightsTo(@PathVariable String arrivalCity) {
		return ResponseEntity.ok(flightService.getFlightsTo(arrivalCity));

	}

	@ApiOperation(value = "Get flight by ID")
	@GetMapping("/{id}")
	public ResponseEntity<FlightDto> getFlight(@PathVariable long id) {
		Flight flight = flightService.getFlight(id);

		if (flight == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return new ResponseEntity<FlightDto>(flight.toDto(), HttpStatus.OK);
	}

	@ApiOperation(value = "Update flight")
	@PutMapping
	public ResponseEntity<FlightDto> updateFlight(@RequestBody @Valid Flight flight) {
		flightService.updateFlight(flight);
		return new ResponseEntity<FlightDto>(flight.toDto(), HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "Create flight")
	@PostMapping
	public ResponseEntity<FlightDto> addFlight(@RequestBody @Valid Flight flight) {
		flightService.addFlight(flight);
		return new ResponseEntity<FlightDto>(flight.toDto(), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Delete flight")
	@DeleteMapping("/{id}")
	public ResponseEntity<FlightDto> deleteFlight(@PathVariable long id) {
		Flight flight = flightService.getFlight(id);

		if (flight == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		flightService.deleteFlight(id);
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

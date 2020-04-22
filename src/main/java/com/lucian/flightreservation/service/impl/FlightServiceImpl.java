package com.lucian.flightreservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucian.flightreservation.dto.FlightDto;
import com.lucian.flightreservation.entities.Flight;
import com.lucian.flightreservation.repository.FlightRepository;
import com.lucian.flightreservation.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Override
	public List<FlightDto> getFlights() {
		List<FlightDto> flights = new ArrayList<>();
		for (Flight flight : flightRepository.findAll())
			flights.add(flight.toDto());
		
		return flights;

	}

	@Override
	public Flight getFlight(long id) {
		return flightRepository.findById(id).orElse(null);
	}

	@Override
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Flight updateFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public void deleteFlight(long id) {
		flightRepository.deleteById(id);
	}

	@Override
	public List<FlightDto> getFlightsFrom(String departureCity) {
		List<FlightDto> flights = new ArrayList<>();
		for (Flight flight : flightRepository.findFlightsFrom(departureCity))
			flights.add(flight.toDto());

		return flights;
	}

	@Override
	public List<FlightDto> getFlightsTo(String arrivalCity) {
		List<FlightDto> flights = new ArrayList<>();
		for (Flight flight : flightRepository.findFlightsTo(arrivalCity))
			flights.add(flight.toDto());

		return flights;
	}

	@Override
	public List<FlightDto> getFlightsFromTo(String departureCity, String arrivalCity) {
		List<FlightDto> flights = new ArrayList<>();
		for (Flight flight : flightRepository.findFlightsFromTo(departureCity, arrivalCity))
			flights.add(flight.toDto());

		return flights;
	}

}

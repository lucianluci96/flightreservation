package com.lucian.flightreservation.service;

import java.util.List;

import com.lucian.flightreservation.dto.FlightDto;
import com.lucian.flightreservation.entities.Flight;

public interface FlightService {

	List<FlightDto> getFlights();

	List<FlightDto> getFlightsFromTo(String departureCity, String arrivalCity);

	List<FlightDto> getFlightsFrom(String departureCity);

	List<FlightDto> getFlightsTo(String arrivalCity);

	Flight getFlight(long id);

	Flight addFlight(Flight flight);

	Flight updateFlight(Flight flight);

	void deleteFlight(long id);

}

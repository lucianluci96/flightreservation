package com.lucian.flightreservation.service;

import java.util.List;

import com.lucian.flightreservation.dto.PassengerDto;
import com.lucian.flightreservation.entities.Passenger;

public interface PassengerService {

	List<PassengerDto> getPassengers();

	Passenger getPassenger(long id);

	Passenger addPassenger(Passenger passenger);

	Passenger updatePassenger(Passenger passenger);

	void deletePassenger(long id);
	
	
}

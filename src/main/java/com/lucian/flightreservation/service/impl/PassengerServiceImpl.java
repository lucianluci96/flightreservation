package com.lucian.flightreservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucian.flightreservation.dto.PassengerDto;
import com.lucian.flightreservation.entities.Passenger;
import com.lucian.flightreservation.repository.PassengerRepository;
import com.lucian.flightreservation.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	@Override
	public List<PassengerDto> getPassengers() {
		List<PassengerDto> passengers = new ArrayList<>();
		for (Passenger passenger : passengerRepository.findAll())
			passengers.add(passenger.toDto());

		return passengers;
	}

	@Override
	public Passenger getPassenger(long id) {
		return passengerRepository.findById(id).get();
	}

	@Override
	public Passenger addPassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	@Override
	public Passenger updatePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	@Override
	public void deletePassenger(long id) {
		passengerRepository.deleteById(id);
	}

}

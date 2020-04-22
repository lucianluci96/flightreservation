package com.lucian.flightreservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lucian.flightreservation.dto.ReservationDto;
import com.lucian.flightreservation.dto.ReservationRequest;
import com.lucian.flightreservation.entities.Flight;
import com.lucian.flightreservation.entities.Passenger;
import com.lucian.flightreservation.entities.Reservation;
import com.lucian.flightreservation.repository.FlightRepository;
import com.lucian.flightreservation.repository.PassengerRepository;
import com.lucian.flightreservation.repository.ReservationRepository;
import com.lucian.flightreservation.service.ReservationService;
import com.lucian.flightreservation.util.EmailUtil;
import com.lucian.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.lucian.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		Long flightId = request.getFlightId();
	
		Flight flight = flightRepository.findById(flightId).orElse(null);

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = ITINERARY_DIR + "_" + savedReservation.getId() + ".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);

		// emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

	@Override
	public List<ReservationDto> getReservations() {
		List<ReservationDto> reservations = new ArrayList<>();
		for (Reservation reservation : reservationRepository.findAll())
			reservations.add(reservation.toDto());

		return reservations;
	}

	@Override
	public Reservation getReservation(long id) {
		return reservationRepository.findById(id).get();
	}

	@Override
	public void deleteReservation(long id) {
		reservationRepository.deleteById(id);
	};
}

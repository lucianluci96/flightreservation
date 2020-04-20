package com.lucian.flightreservation.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucian.flightreservation.dto.ReservationUpdateRequest;
import com.lucian.flightreservation.entities.Reservation;
import com.lucian.flightreservation.service.CheckInService;

@RestController
@RequestMapping("/checkIn")
public class CheckInControllerImpl {

	@Autowired
	CheckInService checkInService;

	@PutMapping
	public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationUpdateRequest request) {
		return ResponseEntity.ok(checkInService.checkInReservation(request));
	}

}

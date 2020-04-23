package com.lucian.flightreservation.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucian.flightreservation.dto.ReservationUpdateRequest;
import com.lucian.flightreservation.entities.Reservation;
import com.lucian.flightreservation.service.CheckInService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/checkIn")
@Api(value = "CheckIn Management System", description = "CheckIn Management System")
public class CheckInControllerImpl {

	@Autowired
	CheckInService checkInService;

	@ApiOperation(value = "CheckIn Reservation")
	@PutMapping
	public ResponseEntity<Reservation> updateReservation(@RequestBody @Valid ReservationUpdateRequest request) {
		return ResponseEntity.ok(checkInService.checkInReservation(request));
	}

}

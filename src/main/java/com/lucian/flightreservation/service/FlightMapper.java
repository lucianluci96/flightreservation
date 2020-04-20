package com.lucian.flightreservation.service;

import java.util.List;

import org.mapstruct.Mapper;

import com.lucian.flightreservation.dto.FlightDto;
import com.lucian.flightreservation.entities.Flight;

@Mapper
public interface FlightMapper {

    List<FlightDto> toFlightDtoList(List<Flight> flights);

    FlightDto toFlightDto(Flight flight);

    Flight toFlight(FlightDto flightDto);
}
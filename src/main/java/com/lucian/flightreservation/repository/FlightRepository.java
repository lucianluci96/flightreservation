package com.lucian.flightreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucian.flightreservation.entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity")
	List<Flight> findFlightsFromTo(@Param("departureCity") String from, @Param("arrivalCity") String to);


	@Query("from Flight where departureCity=:departureCity")
	List<Flight> findFlightsFrom(@Param("departureCity") String from);


	@Query("from Flight where arrivalCity=:arrivalCity")
	List<Flight> findFlightsTo(@Param("arrivalCity") String to);

}

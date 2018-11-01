package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;

/**
 * FlightService
 */
public interface FlightService {
    FlightModel addFlight(FlightModel flight);
    
    void deleteByFlightNumber(String flightNumber);

    Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber);

	List<FlightModel> getAllFlights();
	
	void deleteFlight(FlightModel flight);
	
	Optional<FlightModel> getFlightDetailById(long id);
	
	void updateFlight(Long id, FlightModel flight);
}
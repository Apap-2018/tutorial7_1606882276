package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;
    
    @Override
    public FlightModel addFlight(FlightModel flight) {
        return flightDb.save(flight);
    }

    @Override
    public void deleteByFlightNumber(String flightNumber) {
        flightDb.deleteByFlightNumber(flightNumber);
    }

    @Override
    public Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber);
    }
    
    @Override
    public List<FlightModel> getAllFlights(){
    	return flightDb.findAll();
    }

	@Override
	public void deleteFlight(FlightModel flight) {
		flightDb.delete(flight);
	}

	@Override
	public Optional<FlightModel> getFlightDetailById(long id) {
		return flightDb.findById(id);
	}

	@Override
	public void updateFlight(Long id, FlightModel flight) {
		FlightModel flightU = flightDb.getOne(id);
		flightU.setDestination(flight.getDestination());
		flightU.setOrigin(flight.getOrigin());
		flightU.setTime(flight.getTime());
		flightDb.save(flightU);
//		PilotModel pilotU = pilotDb.getOne(id);
//		pilotU.setName(pilot.getName());
//		pilotU.setFlyHour(pilot.getFlyHour());
//		pilotDb.save(pilotU);
	}
}
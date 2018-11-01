package com.apap.tutorial7.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private PilotService pilotService;
    
    @PostMapping(value="/add")
	public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
		return flightService.addFlight(flight);
	}

    @GetMapping(value="/view/{flightNumber}")
	public FlightModel flightView(@PathVariable("flightNumber") String flightNumber) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber).get();
		return flight;
	}
    
    @GetMapping(value="/view/all")
	public List<FlightModel> FlightView() {
		List<FlightModel> flights = flightService.getAllFlights();
		return flights;
	}
    
    @DeleteMapping(value="/delete/{flightId}")
	public String deletePilot(@PathVariable("flightId") long flightId) {
		//PilotModel pilot = pilotService.getPilotDetailById(pilotId).get();
		//pilotService.deletePilot(pilot);
    	FlightModel flight = flightService.getFlightDetailById(flightId).get();
    	flightService.deleteFlight(flight);
		return "Flight Has Been Deleted";
	}
    
	@PutMapping(value="/update/{flightId}")
	public String updatePilotSubmit(@PathVariable("flightId") long flightId,
			@RequestParam(value = "destination") String destination,
			@RequestParam(value = "origin") String origin,
			@RequestParam(value = "time") String time) throws ParseException {
		//PilotModel pilot = pilotService.getPilotDetailById(pilotId).get();
		FlightModel flight = flightService.getFlightDetailById(flightId).get();
		
		if(flight.equals(null)) {
			System.out.println("MASUK");
			return "Couldn't find your flight";
		}
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf1.parse(time);
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());  
		
		//pilot.setName(name);
		//pilot.setFlyHour(flyHour);
		flight.setDestination(destination);
		flight.setOrigin(origin);
		flight.setTime(sqlStartDate);
		//pilotService.updatePilot(pilotId, pilot);
		flightService.updateFlight(flightId, flight);
		return "Flight Update Success";
	}
   
}
package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private Appointment appointment;


	//create a method post method named bookAppointment with return type ReponseEntity
	//method has parameter of type Appointment, use RequestBody Annotation for mapping
	//save the appointment details to the database and save the response from the method used
	//return http response using ResponseEntity
	@PostMapping
	public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) throws InvalidInputException {
		String appointmentID = appointmentService.appointment(appointment);
		return new ResponseEntity<>(appointmentID, HttpStatus.OK);
	}
	
	
	
	//create a get method named getAppointment with return type as ResponseEntity
	//method has appointmentId of type String. Use PathVariable annotation to identity appointment using the parameter defined
	@GetMapping("/getAppointment/{appointmentId}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable String appointmentID){
		//get the appointment details using the appointmentId
		//save the response
		//return the response as an http response
		return new ResponseEntity<>(appointmentService.getAppointment(appointmentID), HttpStatus.OK);
	}
	

}
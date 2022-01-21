package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RatingsController {

	@Autowired
	private RatingsService ratingsService;


	//create a post method named submitRatings with return type as ResponseEntity
	//define the method parameter rating of type Rating, use @RequestBody for mapping
	@PostMapping("/submitRatings")
	public ResponseEntity<String> submitRatings(@RequestBody Rating rating) {
		//submit the ratings
		ratingsService.submitRatings(rating);
		//return http response with status set to OK
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
}

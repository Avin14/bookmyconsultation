package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Doctor;
import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.repository.DoctorRepository;
import com.upgrad.bookmyconsultation.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RatingsService {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private RatingsRepository ratingsRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	
	//create a method name submitRatings with void return type and parameter of type Rating
	public void submitRatings(Rating rating) {
		//set a UUID for the rating
		//save the rating to the database
		Rating ratingSaved = ratingsRepository.save(rating);
		//get the doctor id from the rating object
		//Get all the ratings for the doctor id
		List<Rating> ratingList = ratingsRepository.findByDoctorId(rating.getDoctorId());
		int sumRating = ratingList
				.stream()
				.map(Rating::getRating)
				.reduce(0, (total,ratingRetrieved) -> total + ratingRetrieved);

		//find that specific doctor with the using doctor id
		Doctor findDoctor = doctorRepository.findById(ratingSaved.getDoctorId()).orElseThrow(ResourceUnAvailableException::new);
		//modify the average rating for that specific doctor by including the new rating
		findDoctor.setRating(Double.valueOf(sumRating)/ratingList.size());
		//save the doctor object to the database
		doctorRepository.save(findDoctor);
	}
}

package com.upgrad.bookmyconsultation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Address {
	@Id
	private String id;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postcode;
}

package com.gti.rest.api.db.entities;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString(exclude = "address")
public class PersonEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "person_name", length = 45, nullable = false)
	private String name;
	private LocalDate dateOfBirth;
	@Column(name = "person_nickname", length = 45, nullable = true)
	private String nickName;

	public int getAge() {
		LocalDate now = LocalDate.now();
		 return Period.between(dateOfBirth,now).getYears();
	}
	

	@ManyToOne
	private AddressEntity address;
	
}
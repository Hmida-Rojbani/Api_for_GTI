package com.gti.rest.api.models;

import java.time.LocalDate;

import com.gti.rest.api.db.entities.AddressEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@NonNull private String name;
	private LocalDate dateOfBirth;
	private String nickName;
	private int age;
	
	private AddressEntity address;

	
}

package com.gti.rest.api.logics;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.gti.rest.api.db.entities.PersonEntity;
import com.gti.rest.api.db.repos.PersonRepository;
import com.gti.rest.api.models.Person;
import com.gti.rest.api.tools.FileTools;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping
public class BLogic {

	private PersonRepository personRepository;
	private ModelMapper mapper;
	private FileTools fileTools;

	public Map<String, List<Person>> personsOverWhat(Predicate<Person> whatCondition) {

		return ((Collection<PersonEntity>) personRepository.findAll()).stream()
				.map(pe -> mapper.map(pe, Person.class))
				.filter(whatCondition)
				.collect(Collectors.groupingBy(p -> p.getAddress().getName()));

	}
	@GetMapping("/test/{age}")
	public Map<String, List<Person>> personsOverAge(@PathVariable("age")int age) {

		return personsOverWhat(p->p.getAge()>age);

	}
	
	@GetMapping("/test/read/{txt}")
	public byte[] readAndEncode64(@PathVariable("txt")String fileName) {

		try {
			return Base64.getEncoder().encode(fileTools.readSource(fileName).getBytes()) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
	@PostMapping("/test/read")
	public String readAndDencode64(@RequestBody String fileName) {

		
			return new String(Base64.getDecoder().decode(fileName));


	}
	

}

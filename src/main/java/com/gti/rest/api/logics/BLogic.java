package com.gti.rest.api.logics;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.gti.rest.api.db.entities.PersonEntity;
import com.gti.rest.api.db.repos.PersonRepository;
import com.gti.rest.api.models.Person;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping
public class BLogic {

	private PersonRepository personRepository;
	private ModelMapper mapper;

	public Map<String, List<Person>> personsOverWhat(Predicate<Person> whatCondition) {

		return ((Collection<PersonEntity>) personRepository.findAll()).stream()
				.map(pe -> mapper.map(pe, Person.class))
				.filter(whatCondition)
				.collect(Collectors.groupingBy(p -> p.getAddress().getName()));

	}
	@GetMapping("/test")
	public Map<String, List<Person>> personsOver20() {

		return personsOverWhat(p->p.getName().startsWith("p"));

	}

}

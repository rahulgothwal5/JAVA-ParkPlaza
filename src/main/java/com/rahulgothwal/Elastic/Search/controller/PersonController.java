package com.rahulgothwal.Elastic.Search.controller;

import com.rahulgothwal.Elastic.Search.document.Person;
import com.rahulgothwal.Elastic.Search.document.dto.PersonDto;
import com.rahulgothwal.Elastic.Search.mapper.impl.PersonMapper;
import com.rahulgothwal.Elastic.Search.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonMapper mapper;

    @Autowired
    private PersonService service;


    @PostMapping
    public ResponseEntity<PersonDto> save(@RequestBody final PersonDto personDto) {
        Person person = mapper.mapFrom(personDto);
        Person res = service.save(person);
        return new ResponseEntity<>(mapper.mapTo(res), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable final String id) {

        Optional<Person> foundPerson = service.findById(id);
        return foundPerson.map(person -> new ResponseEntity<>(mapper.mapTo(person), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> fetchAll() {

        List<PersonDto> list = service.findAllPersons().stream().map(person -> mapper.mapTo(person)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

}

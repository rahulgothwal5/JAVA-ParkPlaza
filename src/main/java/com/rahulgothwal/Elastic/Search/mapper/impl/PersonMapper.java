package com.rahulgothwal.Elastic.Search.mapper.impl;

import com.rahulgothwal.Elastic.Search.document.Person;
import com.rahulgothwal.Elastic.Search.document.dto.PersonDto;
import com.rahulgothwal.Elastic.Search.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements Mapper<Person, PersonDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto mapTo(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Person mapFrom(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }
}

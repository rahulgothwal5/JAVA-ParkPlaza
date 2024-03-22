package com.rahulgothwal.Elastic.Search.service;

import com.rahulgothwal.Elastic.Search.document.Person;
import com.rahulgothwal.Elastic.Search.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(final Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> findById(final String id) {

        return personRepository.findById(id);
    }

    public List<Person> findAllPersons() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(
                list::add
        );
        return list;
    }
}

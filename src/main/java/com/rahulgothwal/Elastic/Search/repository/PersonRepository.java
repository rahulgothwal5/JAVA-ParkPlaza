package com.rahulgothwal.Elastic.Search.repository;

import com.rahulgothwal.Elastic.Search.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
}

package com.rahulgothwal.Elastic.Search.repository;

import com.rahulgothwal.Elastic.Search.document.Vehicle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends ElasticsearchRepository<Vehicle, String> {
}

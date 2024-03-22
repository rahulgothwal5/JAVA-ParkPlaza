package com.rahulgothwal.Elastic.Search.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahulgothwal.Elastic.Search.constants.Indices;
import com.rahulgothwal.Elastic.Search.document.Vehicle;
import com.rahulgothwal.Elastic.Search.repository.VehicleRepository;
import com.rahulgothwal.Elastic.Search.search.SearchRequestDTO;
import com.rahulgothwal.Elastic.Search.search.SearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class VehicleService {

    //    @Autowired
//    @Value("${spring.data.elasticsearch.cluster-node}")
//    public String elasticsearchUrl;
    @Autowired
    private VehicleRepository repository;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestClientBuilder builder;


    public Vehicle save(final Vehicle person) {
        return repository.save(person);
    }

    public Optional<Vehicle> findById(final String id) {

        return repository.findById(id);
    }

    public List<Vehicle> findAllVehicle() {
        List<Vehicle> list = new ArrayList<>();
        repository.findAll().forEach(
                list::add
        );
        return list;
    }

    public Page<Vehicle> searchSimilarToEntity(String query, String[] fields, Pageable pageable) {
        Vehicle vehicle = Vehicle.builder().build();
        vehicle.setNumber(query);
        return repository.searchSimilar(vehicle, fields, pageable);

    }

    public List<Vehicle> search(final SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.VEHICLE_INDEX,
                dto
        );

        return searchInternal(request);
    }

    private List<Vehicle> searchInternal(final SearchRequest request) {
        if (request == null) {
            log.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {

            final ClientConfiguration config = ClientConfiguration.builder()
                    .connectedTo("localhost:9200")
                    .build();

            RestHighLevelClient client = new RestHighLevelClient(builder);


            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Vehicle> vehicles = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                vehicles.add(mapper.readValue(hit.getSourceAsString(), Vehicle.class));
            }
            return vehicles;
        } catch (IOException e) {
            log.error("Error occurred while processing search response: {}", e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<Vehicle> getAllVehiclesCreatedSince(final Date date) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.VEHICLE_INDEX,
                "created",
                date
        );
        return searchInternal(request);
    }

    public List<Vehicle> searchCreatedSince(SearchRequestDTO dto, Date date) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.VEHICLE_INDEX,
                dto,
                date
        );

        return searchInternal(request);
    }
}

package com.rahulgothwal.Elastic.Search.controller;

import com.rahulgothwal.Elastic.Search.document.Vehicle;
import com.rahulgothwal.Elastic.Search.document.dto.VehicleDto;
import com.rahulgothwal.Elastic.Search.mapper.impl.VehicleMapper;
import com.rahulgothwal.Elastic.Search.search.SearchRequestDTO;
import com.rahulgothwal.Elastic.Search.service.VehicleService;
import com.rahulgothwal.Elastic.Search.service.helper.VehicleDummyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    @Autowired
    private VehicleMapper mapper;

    @Autowired
    private VehicleService service;
    @Autowired
    private VehicleDummyDataService dummyDataService;


    @PostMapping
    public ResponseEntity<VehicleDto> save(@RequestBody final VehicleDto personDto) {
        Vehicle vehicle = mapper.mapFrom(personDto);
        Vehicle res = service.save(vehicle);
        return new ResponseEntity<>(mapper.mapTo(res), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> findById(@PathVariable final String id) {

        Optional<Vehicle> foundVehicle = service.findById(id);
        return foundVehicle.map(person -> new ResponseEntity<>(mapper.mapTo(person), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping()
    public ResponseEntity<List<VehicleDto>> fetchAll() {
        List<VehicleDto> list = service.findAllVehicle().stream().map(person -> mapper.mapTo(person)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<VehicleDto>> search(@RequestBody final SearchRequestDTO dto) {
        List<VehicleDto> list = service.search(dto).stream().map(vehicle -> mapper.mapTo(vehicle)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/{date}")
    public ResponseEntity<List<VehicleDto>> getAllVehiclesCreatedSince(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") final Date date) {
        List<VehicleDto> list = service.getAllVehiclesCreatedSince(date).stream().map(vehicle -> mapper.mapTo(vehicle)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/insertdummydata")
    public void insertDummyData() {
        dummyDataService.insertDummyData();
    }

    @PostMapping("/searchcreatedsince/{date}")
    public List<Vehicle> searchCreatedSince(
            @RequestBody final SearchRequestDTO dto,
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd") final Date date) {
        return service.searchCreatedSince(dto, date);
    }

}

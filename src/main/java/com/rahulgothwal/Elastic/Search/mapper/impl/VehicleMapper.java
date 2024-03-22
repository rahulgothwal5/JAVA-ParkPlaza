package com.rahulgothwal.Elastic.Search.mapper.impl;

import com.rahulgothwal.Elastic.Search.document.Vehicle;
import com.rahulgothwal.Elastic.Search.document.dto.VehicleDto;
import com.rahulgothwal.Elastic.Search.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper implements Mapper<Vehicle, VehicleDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VehicleDto mapTo(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDto.class);
    }

    @Override
    public Vehicle mapFrom(VehicleDto vehicleDto) {
        return modelMapper.map(vehicleDto, Vehicle.class);
    }
}

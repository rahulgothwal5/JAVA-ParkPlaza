package com.rahulgothwal.Elastic.Search.document.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {

    private String id;

    private String number;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;

}

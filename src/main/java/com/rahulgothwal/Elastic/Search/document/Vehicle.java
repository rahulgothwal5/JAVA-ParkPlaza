package com.rahulgothwal.Elastic.Search.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rahulgothwal.Elastic.Search.constants.Indices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = Indices.VEHICLE_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Vehicle {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String number;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;

}

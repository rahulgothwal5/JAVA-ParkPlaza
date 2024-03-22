package com.rahulgothwal.Elastic.Search.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagedRequestDTO {
    private static final int DEFAULT_SIZE = 100;

    private int page = DEFAULT_SIZE;
    private int size;
}

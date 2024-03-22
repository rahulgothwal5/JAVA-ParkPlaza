package com.rahulgothwal.Elastic.Search.service;

import com.rahulgothwal.Elastic.Search.constants.Indices;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IndexService {
    private static final List<String> INDICES = List.of(Indices.VEHICLE_INDEX, Indices.PERSON_INDEX);

    private final RestHighLevelClient client;

    @Autowired
    public IndexService(RestClientBuilder builder) {
        this.client = new RestHighLevelClient(builder);
    }

    public void recreateIndices() {

        for (final String indexName : INDICES) {
            try {
                final boolean indexExists = client
                        .indices()
                        .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if (indexExists) {
                    client.indices().delete(
                            new DeleteIndexRequest(indexName),
                            RequestOptions.DEFAULT
                    );
                }
            } catch (final Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}

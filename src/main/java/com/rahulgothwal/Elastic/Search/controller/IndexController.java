package com.rahulgothwal.Elastic.Search.controller;

import com.rahulgothwal.Elastic.Search.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class IndexController {
    @Autowired
    private IndexService service;


    @PostMapping("/recreate")
    public void recreateAllIndices() {
        service.recreateIndices();
    }
}

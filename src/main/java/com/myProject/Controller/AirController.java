package com.myProject.Controller;

import com.myProject.Services.searchAirDataService;
import com.myProject.entity.weather;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class AirController {
    private searchAirDataService searchAirDataService;

    @Inject
    public AirController(com.myProject.Services.searchAirDataService searchAirDataService) {
        this.searchAirDataService = searchAirDataService;
    }

    @RequestMapping("/")
    public List<weather> index() {
        return searchAirDataService.getAirData();

    }
}

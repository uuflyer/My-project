package com.myProject.Services;

import com.myProject.DAO.dataDao;
import com.myProject.entity.weather;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class searchAirDataService {
    private dataDao dataDao;

    @Inject
    public searchAirDataService(dataDao dataDao) {
        this.dataDao = dataDao;
    }

    public List<weather> getAirData() {
        return dataDao.getDataFromDB();


    }
}

package com.myProject.DAO;

import com.myProject.entity.weather;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class dataDao {
    private final SqlSession sqlSession;

    @Inject
    public dataDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    public List<weather> getDataFromDB() {
        return sqlSession.selectList("myMapper.selectAllData");
    }

}

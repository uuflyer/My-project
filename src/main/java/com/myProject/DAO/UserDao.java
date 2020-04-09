package com.myProject.DAO;

import com.myProject.entity.User;
import com.myProject.entity.UserResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDao {
    private final SqlSession sqlSession;

    @Inject
    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public User updateUserInfo(User updateUser) {
        sqlSession.update("updateUserInfo", updateUser);
        return selectUserById(updateUser.getId());
    }

    private Map<String, Object> asMap(Object... args) {
        Map<String, Object> result = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            result.put(args[i].toString(), args[i + 1]);
        }
        return result;
    }

    public User getUserByUsername(String username) {
        return sqlSession.selectOne("getUserByUsername", username);

    }

    public User selectUserById(int userId) {
        return sqlSession.selectOne("selectUserById", userId);
    }

    public User getUserByTel(String tel) {
        return sqlSession.selectOne("getUserByTel", tel);
    }

    public void save(User user) {
        sqlSession.insert("insertUserInfo", user);
    }

}

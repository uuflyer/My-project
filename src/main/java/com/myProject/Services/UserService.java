package com.myProject.Services;

import com.myProject.entity.User;
import com.myProject.DAO.UserDao;
import com.myProject.entity.UserResult;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private UserDao userDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    public UserService(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDao = userDao;
    }

    public void save(String username, String password, String tel, String mail) {
        User user = new User();
        user.setUsername(username);
        user.setEncryptedpassword(bCryptPasswordEncoder.encode(password));
        user.setTel(tel);
        userDao.save(user);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public UserResult getUserInfoByUsername(String username) {
        return UserResult.success(getUserByUsername(username), "获取成功");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "不存在");
        }
        return new org.springframework.security.core.userdetails.User(username, user.getEncryptedpassword(), Collections.emptyList());
    }

    public User getUserByTel(String tel) {
        return userDao.getUserByTel(tel);
    }

    public UserResult updateUserInfo(User updateUser) {
        User userInDB = getUserByUsername(updateUser.getUsername());
        if (userInDB == null) {
            return UserResult.failure("用户不存在");

        }
        return UserResult.success(userDao.updateUserInfo(updateUser), "更新成功");
    }
}

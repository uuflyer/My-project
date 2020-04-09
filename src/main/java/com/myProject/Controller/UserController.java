package com.myProject.Controller;

import com.myProject.Services.AuthService;
import com.myProject.Services.UserService;
import com.myProject.entity.User;
import com.myProject.entity.UserResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @Inject
    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public UserResult getUserInfo() {
        try {
            return authService.getCurrentUser()
                    .map(user -> userService.getUserInfoByUsername(user.getUsername()))
                    .orElse(UserResult.failure("无法获取别人的信息"));
        } catch (IllegalArgumentException e) {
            return UserResult.failure(e.getMessage());
        }
    }

    @PostMapping("/userInfo")
    @ResponseBody
    public UserResult updateUser(@RequestBody Map<String, String> param) {

        try {
            return authService.getCurrentUser()
                    .map(user -> userService.updateUserInfo(fromParam(user, param)))
                    .orElse(UserResult.failure("登录后才能操作"));
        } catch (IllegalArgumentException e) {
            return UserResult.failure(e.getMessage());
        }
    }

    private User fromParam(User user, Map<String, String> param) {
        user.setTel(param.get("tel"));
        user.setHomeAddress(param.get("HomeAddress"));
        user.setIpAddress(param.get("IpAddress"));
        user.setMail(param.get("email"));
        return user;
    }
}

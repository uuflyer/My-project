package com.myProject.Controller;

import com.myProject.Services.UserService;
import com.myProject.entity.LoginResult;
import com.myProject.entity.Result;
import com.myProject.entity.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private static final Pattern pattern = Pattern.compile("1\\d{10}");

    @Inject
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping("/auth")
    @ResponseBody
    public Result auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.getUserByUsername(authentication == null ? null : authentication.getName());
        if (loggedInUser == null) {
            System.out.println("用户没有登录");
            return LoginResult.successExecute("用户没有登录");
        } else {
            System.out.println("登录成功");
            return LoginResult.successLogin(null, loggedInUser);
        }
    }

    @CrossOrigin
    @PostMapping("/auth/login")
    @ResponseBody
    public Object login(@RequestBody Map<String, Object> usernameAndPasswordJson, HttpServletRequest request) {
        UserDetails userDetails;
//        if (request.getHeader("user-agent") == null || !request.getHeader("user-agent").contains("Mozilla")) {
//            return "爬虫不可以";
//        }
        String username = usernameAndPasswordJson.get("username").toString();
        String password = usernameAndPasswordJson.get("password").toString();
        Matcher matcher = pattern.matcher(username);

        try {
            if (matcher.find()) {
                username = userService.getUserByTel(username).getUsername();
            }
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return LoginResult.failure("用户不存在" + username);
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        try {

            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);

            request.setAttribute("user", userDetails);

            return LoginResult.successLogin("登录成功", userService.getUserByUsername(username));
        } catch (BadCredentialsException e) {
            return LoginResult.failure("密码不正确");
        }
    }

    @CrossOrigin
    @PostMapping("/auth/register")
    @ResponseBody
    public Result register(@RequestBody Map<String, Object> userInfo) {
        String password = userInfo.get("password").toString();
        String tel = userInfo.get("tel").toString();

        String usernaem = getRandomUsername();
        if (password.length() < 6 || password.length() > 16) {
            return LoginResult.failure("密码只允许6-16个字符");
        }
        try {
            userService.save(usernaem, password, tel, null);
        } catch (DuplicateKeyException e) {
            return LoginResult.failure("该手机号已注册");
        }
        return LoginResult.successExecute("注册成功");
    }


    private String getRandomUsername() {
        StringBuilder username = new StringBuilder();
        StringBuilder charOrNum = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            charOrNum.replace(0, 4, random.nextInt(2) % 2 != 0 ? "num" : "char");
            if ("char".equalsIgnoreCase(charOrNum.toString())) {
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                username.append((char) (random.nextInt(26) + temp));
            } else {
                username.append(random.nextInt(10));
            }
        }
        User usernameInDb = userService.getUserByUsername(username.toString());

        if (usernameInDb != null) {
            getRandomUsername();
        }
        return username.toString();
    }
}

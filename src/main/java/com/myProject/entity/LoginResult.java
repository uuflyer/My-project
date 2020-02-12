package com.myProject.entity;

public class LoginResult extends Result<User> {
    boolean isLogin;

    public boolean isLogin() {
        return this.isLogin;
    }

    public LoginResult(ResultStatus status, String msg, User user, boolean isLogin) {
        super(status, msg, user);
        this.isLogin = isLogin;
    }

    public static Result successExecute(String msg) {
        return new LoginResult(ResultStatus.OK, msg, null, false);
    }

    public static Result successLogin(String msg, User user) {
        return new LoginResult(ResultStatus.OK, msg, user, true);
    }

    public static Result failure(String msg) {
        return new LoginResult(ResultStatus.FAIL, msg, null, false);
    }

}

package com.myProject.entity;

public class UserResult extends Result<User> {

    private UserResult(ResultStatus status, String msg, User data) {
        super(status, msg, data);
    }

    public static UserResult success(User user, String msg) {
        return new UserResult(ResultStatus.OK, msg, user);
    }

    public static UserResult failure(String msg) {
        return new UserResult(ResultStatus.FAIL, msg, null);
    }
}
